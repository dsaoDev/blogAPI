package com.treinaRecife.BlogAPI.service;

import com.treinaRecife.BlogAPI.dto.request.ComentarioRequest;
import com.treinaRecife.BlogAPI.dto.response.ComentarioResponse;
import com.treinaRecife.BlogAPI.dto.response.PostResponseComComentarios;
import com.treinaRecife.BlogAPI.exceptions.PostNotFoundException;
import com.treinaRecife.BlogAPI.mapper.ComentarioMapper;
import com.treinaRecife.BlogAPI.model.Comentario;
import com.treinaRecife.BlogAPI.model.Post;
import com.treinaRecife.BlogAPI.repository.ComentarioRepository;
import com.treinaRecife.BlogAPI.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    private final PostRepository postRepository;

    private final ComentarioMapper comentarioMapper;

    public ComentarioResponse salvarComentariosToPostPeloIdPost(Long idPost, ComentarioRequest comentarioRequest) {
        var postEntidade = returnPost(idPost);

        var comentarioEntidade = comentarioMapper.requestDtoParaEntidade(comentarioRequest);

        comentarioEntidade.setPost(postEntidade);

        postEntidade.getComentarios().add(comentarioEntidade);

        comentarioRepository.save(comentarioEntidade);

        return comentarioMapper.deEntidadeParaResponseDTO(comentarioEntidade);
    }

        public Page<ComentarioResponse> paginarComentariosByPostId(Long postId, Pageable pageable){
            Page<ComentarioResponse> comentariosResponse;
            comentariosResponse = comentarioMapper.converterPaginaDeEntidadeParaResponseDTO(comentarioRepository.findComentarioByPostId(postId,pageable));
            return comentariosResponse;
        }


    private Post returnPost(Long idPost) {
        return postRepository.findById(idPost).orElseThrow(() -> new PostNotFoundException("Post com id " + idPost + " Não encontrado"));
    }


}
