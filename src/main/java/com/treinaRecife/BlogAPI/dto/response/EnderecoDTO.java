package com.treinaRecife.BlogAPI.dto.response;

import com.treinaRecife.BlogAPI.model.Endereco;
import lombok.Getter;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Getter
public class EnderecoDTO {
    private String cep;

    private String logradouro;

    private String complemento;

    private String bairro;

    private String localidade;

    private String uf;

    public EnderecoDTO(Endereco endereco) {
        cep = endereco.getCep();
        logradouro = endereco.getLogradouro();
        complemento = endereco.getComplemento();
        bairro = endereco.getBairro();
        localidade = endereco.getLocalidade();
        uf = endereco.getUf();
    }

}
