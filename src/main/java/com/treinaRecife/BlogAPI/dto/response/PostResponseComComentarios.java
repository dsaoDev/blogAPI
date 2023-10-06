package com.treinaRecife.BlogAPI.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@NoArgsConstructor
@Getter
@Setter
public class PostResponseComComentarios {

    private Long idPost;

    private String titulo;

    private String conteudo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataDeCriacao;

    private Long idAutor;


    private List<ComentarioResponse> comentarios;




}
