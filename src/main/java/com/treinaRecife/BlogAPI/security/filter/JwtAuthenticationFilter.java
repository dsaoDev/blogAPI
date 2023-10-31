package com.treinaRecife.BlogAPI.security.filter;

import com.treinaRecife.BlogAPI.security.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwtToken = extractJwtToken(authHeader);
        String userEmail = extractUserEmailFromToken(jwtToken);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = loadUserDetails(userEmail);

            if (jwtService.isTokenValid(jwtToken, userDetails)) {
                setAuthentication(userDetails, request);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String extractJwtToken(String authHeader) {
        return authHeader.substring(7);
    }

    private String extractUserEmailFromToken(String jwtToken) {
        return jwtService.extractUsername(jwtToken);
    }

    private UserDetails loadUserDetails(String userEmail) {
        return this.userDetailsService.loadUserByUsername(userEmail);
    }

    private void setAuthentication(UserDetails userDetails, HttpServletRequest request) {

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        securityContext.setAuthentication(authToken);

        SecurityContextHolder.setContext(securityContext);
    }


    }

