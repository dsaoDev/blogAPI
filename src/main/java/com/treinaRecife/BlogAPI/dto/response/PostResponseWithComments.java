package com.treinaRecife.BlogAPI.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.treinaRecife.BlogAPI.mapper.ComentarioMapper;
import com.treinaRecife.BlogAPI.model.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostResponseWithComments {

    private Long idPost;

    private String titulo;

    private String conteudo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataDeCriacao;

    private Long idAutor;

    private List<ComentarioResponse> comentarioResponses;
    public PostResponseWithComments(Post postEntidade) {
        this.idPost = postEntidade.getIdPost();
        this.titulo = postEntidade.getTitulo();
        this.conteudo = postEntidade.getConteudo();
        this.dataDeCriacao = postEntidade.getDataDeCriacao();
        this.idAutor = postEntidade.getAutor().getIdAutor();
        this.comentarioResponses = postEntidade.getComentarios().stream().map(ComentarioMapper::deEntidadeParaResponseDTOStaticVersion).toList();
    }


}
