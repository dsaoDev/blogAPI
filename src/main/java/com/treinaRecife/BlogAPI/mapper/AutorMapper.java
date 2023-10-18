package com.treinaRecife.BlogAPI.mapper;

import com.treinaRecife.BlogAPI.dto.request.AutorRequest;
import com.treinaRecife.BlogAPI.dto.response.AutorResponse;
import com.treinaRecife.BlogAPI.dto.response.EnderecoDTO;
import com.treinaRecife.BlogAPI.model.Autor;
import com.treinaRecife.BlogAPI.model.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class AutorMapper implements Mapper<AutorRequest, Autor, AutorResponse> {


    public static EnderecoDTO converterEnderecoEntidadeParaEnderecoDTO(Endereco endereco) {
        return new EnderecoDTO(endereco);
    }

    @Override
    public Autor requestDtoParaEntidade(AutorRequest autorRequest) {
        return Autor.builder().nome(autorRequest.getNome())
                .sobreNome(autorRequest.getSobreNome())
               .build();
    }

    @Override
    public AutorResponse deEntidadeParaResponseDTO(Autor autor) {
        return new AutorResponse(autor);
    }

    @Override
    public Page<AutorResponse> converterPaginaDeEntidadeParaResponseDTO(Page<Autor> paginaDeEntidade) {
        return paginaDeEntidade.map(this::deEntidadeParaResponseDTO);
    }
}
