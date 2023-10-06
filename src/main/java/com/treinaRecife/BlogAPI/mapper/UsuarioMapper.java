package com.treinaRecife.BlogAPI.mapper;

import com.treinaRecife.BlogAPI.dto.request.UsuarioRequest;
import com.treinaRecife.BlogAPI.dto.response.UsuarioResponse;
import com.treinaRecife.BlogAPI.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper implements Mapper<UsuarioRequest, Usuario, UsuarioResponse> {


    @Override
    public Usuario requestDtoParaEntidade(UsuarioRequest usuarioRequest) {
        return Usuario.builder()
                .nome(usuarioRequest.getNome())
                .sobreNome(usuarioRequest.getSobreNome())
                .email(usuarioRequest.getEmail())
                .senha(usuarioRequest.getSenha())
                .build();
    }

    @Override
    public UsuarioResponse deEntidadeParaResponseDTO(Usuario usuario) {
        return new UsuarioResponse(usuario);
    }
}