package com.treinaRecife.BlogAPI.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsuarioRequest {


    private String nome;


    private String sobreNome;


    private String email;


    private String senha;
}
