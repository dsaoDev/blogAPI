package com.treinaRecife.BlogAPI.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.treinaRecife.BlogAPI.model.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostResponse {

    private Long idPost;

    private String titulo;

    private String conteudo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataDeCriacao;


    private Long idAutor;

    //Conversão
    public PostResponse(Post postEntity){
        this.idPost = postEntity.getIdPost();
        this.titulo = postEntity.getTitulo();
        this.conteudo = postEntity.getConteudo();
        this.dataDeCriacao = postEntity.getDataDeCriacao();
        this.idAutor = postEntity.getAutor().getIdUsuario();
    }
}
