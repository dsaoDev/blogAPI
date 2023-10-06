package com.treinaRecife.BlogAPI.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.treinaRecife.BlogAPI.model.Comentario;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ComentarioResponse {

    private Long idComentario;


    private String texto;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataDeCriacao;


    private Long idAutor;


    private Long idPost;

    //Conversão
    public ComentarioResponse(Comentario comentarioEntity) {
        this.idComentario = comentarioEntity.getIdComentario();
        this.texto = comentarioEntity.getTexto();
        this.dataDeCriacao = comentarioEntity.getDataDeCriacao();
        this.idAutor = comentarioEntity.getAutor().getIdUsuario();
        this.idPost = comentarioEntity.getPost().getIdPost();
    }
}
