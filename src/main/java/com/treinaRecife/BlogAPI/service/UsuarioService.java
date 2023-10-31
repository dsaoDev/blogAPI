package com.treinaRecife.BlogAPI.service;

import com.treinaRecife.BlogAPI.dto.request.AutenticacaoRequest;
import com.treinaRecife.BlogAPI.dto.request.RegistroRequest;
import com.treinaRecife.BlogAPI.dto.response.RegisterResponse;
import com.treinaRecife.BlogAPI.dto.response.TokenDTO;
import com.treinaRecife.BlogAPI.exceptions.CustomBadCredentialsException;
import com.treinaRecife.BlogAPI.exceptions.EntidadeNotFoundException;
import com.treinaRecife.BlogAPI.mapper.AutenticacaoMapper;
import com.treinaRecife.BlogAPI.model.Usuario;
import com.treinaRecife.BlogAPI.repository.UsuarioRepository;
import com.treinaRecife.BlogAPI.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final AutenticacaoMapper autenticacaoMapper;

    private final UsuarioRepository usuarioRepository;

    private final FazValidacoesService fazValidacoesService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    public RegisterResponse registrarUsuario(RegistroRequest registroRequest) {
        fazValidacoesService.checarSeEmailJaExisteNoBancoDeDados(registroRequest.getEmail());

        var usuarioEntidade = autenticacaoMapper.deUsuarioRequestParaUsuarioModel(registroRequest);

        usuarioRepository.save(usuarioEntidade);

        return RegisterResponse.builder()
                .dataDeCriacao(usuarioEntidade.getDataDeCriacao())
                .mensagem("Cadastro realizado com Sucesso")
                .build();
    }

    public TokenDTO autenticarUsuario(AutenticacaoRequest autenticacaoRequest) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    autenticacaoRequest.getEmail(),
                    autenticacaoRequest.getSenha()
            ));

        } catch (BadCredentialsException e) {
            throw new CustomBadCredentialsException("Senha incorreta");
        }

        var usuarioEntidade = usuarioRepository.findByEmail(autenticacaoRequest.getEmail()).orElseThrow(() -> new EntidadeNotFoundException("Email não encontrado :" + autenticacaoRequest.getEmail()));

        var jwtToken = jwtService.generateToken(usuarioEntidade);

        return TokenDTO.builder().token(jwtToken).build();
    }
}
