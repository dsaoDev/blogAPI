package com.treinaRecife.BlogAPI.mapper;

import com.treinaRecife.BlogAPI.dto.request.UsuarioRequest;
import com.treinaRecife.BlogAPI.dto.response.EnderecoDTO;
import com.treinaRecife.BlogAPI.dto.response.UsuarioResponse;
import com.treinaRecife.BlogAPI.model.Endereco;
import com.treinaRecife.BlogAPI.model.Usuario;
import org.springframework.data.domain.Page;
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

    @Override
    public Page<UsuarioResponse> converterPaginaDeEntidadeParaResponseDTO(Page<Usuario> paginaDeEntidade) {
        return paginaDeEntidade.map(this::deEntidadeParaResponseDTO);
    }

    public static EnderecoDTO converterEnderecoEntidadeParaEnderecoDTO(Endereco endereco){
        return new EnderecoDTO(endereco);
    }
}
