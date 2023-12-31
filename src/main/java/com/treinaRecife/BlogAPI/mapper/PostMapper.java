package com.treinaRecife.BlogAPI.mapper;

import com.treinaRecife.BlogAPI.dto.request.PostRequest;
import com.treinaRecife.BlogAPI.dto.response.PostResponse;
import com.treinaRecife.BlogAPI.dto.response.PostResponseWithComments;
import com.treinaRecife.BlogAPI.model.Autor;
import com.treinaRecife.BlogAPI.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PostMapper implements Mapper<PostRequest, Post, PostResponse> {


    @Override
    public Post requestDtoParaEntidade(PostRequest postRequest) {
        var autorEntidade = new Autor();
        autorEntidade.setIdAutor(postRequest.getIdAutor());

        return Post.builder().titulo(postRequest.getTitulo()).conteudo(postRequest.getConteudo()).autor(autorEntidade).build();
    }

    @Override
    public PostResponse deEntidadeParaResponseDTO(Post post) {
        return new PostResponse(post);
    }

    @Override
    public Page<PostResponse> converterPaginaDeEntidadeParaResponseDTO(Page<Post> paginaDeEntidade) {
        return paginaDeEntidade.map(this::deEntidadeParaResponseDTO);
    }

    public PostResponseWithComments converteDePostEntidadeParaPostComComentariosDTO(Post postEntidade) {
        return new PostResponseWithComments(postEntidade);
    }


}

