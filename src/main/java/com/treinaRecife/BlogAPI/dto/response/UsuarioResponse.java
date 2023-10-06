package com.treinaRecife.BlogAPI.dto.response;

import com.treinaRecife.BlogAPI.model.Usuario;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsuarioResponse {

    private Long idUsuario;


    private String nome;


    private String sobreNome;


    private String email;


    private String senha;

    public UsuarioResponse(Usuario autorEntity){
        this.idUsuario = autorEntity.getIdUsuario();
        this.nome = autorEntity.getNome();
        this.sobreNome = autorEntity.getSobreNome();
        this.email = autorEntity.getEmail();
        this.senha = autorEntity.getSenha();
    }
}
