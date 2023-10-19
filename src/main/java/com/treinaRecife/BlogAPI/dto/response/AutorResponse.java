package com.treinaRecife.BlogAPI.dto.response;

import com.treinaRecife.BlogAPI.mapper.AutorMapper;
import com.treinaRecife.BlogAPI.model.Autor;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@NoArgsConstructor
@Setter
public class AutorResponse {

    private Long idAutor;


    private String nome;


    private String sobreNome;

    private String email;


    private String cpf;
    

    private EnderecoDTO endereco;

    public AutorResponse(Autor autorEntity) {
        this.idAutor = autorEntity.getIdAutor();
        this.nome = autorEntity.getNome();
        this.sobreNome = autorEntity.getSobreNome();
        this.endereco = AutorMapper.converterEnderecoEntidadeParaEnderecoDTO(autorEntity.getEndereco());
        this.cpf = autorEntity.getCpf();
        this.email = autorEntity.getEmail();

    }
}
