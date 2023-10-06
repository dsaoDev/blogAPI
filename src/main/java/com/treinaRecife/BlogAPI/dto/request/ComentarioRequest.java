package com.treinaRecife.BlogAPI.dto.request;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ComentarioRequest {



    private String texto;


    private Long idAutor;


    private Long idPost;
}
