package com.treinaRecife.BlogAPI.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequest {


    private String titulo;


    private String conteudo;


    private Long idAutor;
}
