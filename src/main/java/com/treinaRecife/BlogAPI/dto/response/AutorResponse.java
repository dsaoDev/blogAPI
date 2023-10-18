package com.treinaRecife.BlogAPI.dto.response;

import com.treinaRecife.BlogAPI.mapper.AutorMapper;
import com.treinaRecife.BlogAPI.model.Autor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AutorResponse {

    private Long idAutor;


    private String nome;


    private String sobreNome;
    

    private EnderecoDTO endereco;

    public AutorResponse(Autor autorEntity) {
        this.idAutor = autorEntity.getIdAutor();
        this.nome = autorEntity.getNome();
        this.sobreNome = autorEntity.getSobreNome();
        this.endereco = AutorMapper.converterEnderecoEntidadeParaEnderecoDTO(autorEntity.getEndereco());
    }
}
