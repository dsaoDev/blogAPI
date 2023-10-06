package com.treinaRecife.BlogAPI.mapper;

import com.treinaRecife.BlogAPI.dto.request.PostRequest;
import com.treinaRecife.BlogAPI.dto.response.PostResponse;
import com.treinaRecife.BlogAPI.model.Post;
import com.treinaRecife.BlogAPI.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public Post dePostRequestParaEntidadePost (PostRequest postRequest){
        var autorEntidade = new Usuario();
        autorEntidade.setIdUsuario(postRequest.getIdAutor());

        return Post.builder()
                .titulo(postRequest.getTitulo())
                .conteudo(postRequest.getConteudo())
                .autor(autorEntidade)
                .build();
    }

    public PostResponse dePostEntidadeParaPostResponse(Post post){
        return new PostResponse(post);
    }

}
