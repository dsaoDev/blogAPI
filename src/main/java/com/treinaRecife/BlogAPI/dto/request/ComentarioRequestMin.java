package com.treinaRecife.BlogAPI.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ComentarioRequestMin {

    private String texto;


    private Long idAutor;
}
