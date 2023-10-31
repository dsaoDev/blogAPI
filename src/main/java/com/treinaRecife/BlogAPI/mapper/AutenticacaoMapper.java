package com.treinaRecife.BlogAPI.mapper;

import com.treinaRecife.BlogAPI.dto.request.RegistroRequest;
import com.treinaRecife.BlogAPI.model.Usuario;
import com.treinaRecife.BlogAPI.model.constants.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AutenticacaoMapper {

    private final PasswordEncoder passwordEncoder;
    public Usuario deUsuarioRequestParaUsuarioModel(RegistroRequest registroRequest){
        return Usuario.builder()
                .nome(registroRequest.getNome())
                .email(registroRequest.getEmail())
                .senha(passwordEncoder.encode(registroRequest.getSenha()))
                .role(Role.USER)
                .build();
    }
}
