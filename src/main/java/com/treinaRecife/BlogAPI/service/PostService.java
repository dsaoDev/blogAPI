package com.treinaRecife.BlogAPI.service;

import com.treinaRecife.BlogAPI.dto.request.PostRequest;
import com.treinaRecife.BlogAPI.dto.response.PostResponse;
import com.treinaRecife.BlogAPI.exceptions.PostNotFoundException;
import com.treinaRecife.BlogAPI.mapper.PostMapper;
import com.treinaRecife.BlogAPI.model.Post;
import com.treinaRecife.BlogAPI.model.Usuario;
import com.treinaRecife.BlogAPI.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;

    private final PostRepository postRepository;

    public PostResponse salvarPost (PostRequest postRequest){
        var postEntidade = postMapper.requestDtoParaEntidade(postRequest);

        postRepository.save(postEntidade);

        return postMapper.deEntidadeParaResponseDTO(postEntidade);
    }

    public PostResponse acharPostPorId(Long idPost){
        var postEntidade = returnPost(idPost);
        return postMapper.deEntidadeParaResponseDTO(postEntidade);
    }

    public Page<PostResponse> paginarPosts(Pageable pageable){
        return postMapper.converterPaginaDeEntidadeParaResponseDTO(postRepository.findAll(pageable));
    }

    public PostResponse atualizarPostPorId(Long idPost, PostRequest postRequest){
        var postEntidade = returnPost(idPost);

        atualizarDadosPost(postRequest,postEntidade);

        return postMapper.deEntidadeParaResponseDTO(postEntidade);
    }

    public void deletarPostPorId(Long idPost){
        var postEntidade = returnPost(idPost);

        postRepository.deleteById(postEntidade.getIdPost());
    }


    //Metodo auxiliar
    private Post returnPost(Long idPost){
        return postRepository.findById(idPost).orElseThrow
                (() -> new PostNotFoundException("Post com id " + idPost + " Não encontrado"));
    }
    //Metodo Auxiliar
    private void atualizarDadosPost(PostRequest postRequest, Post postEntidade){
        Usuario autorEntidade = new Usuario();
        autorEntidade.setIdUsuario(postRequest.getIdAutor());

        postEntidade.setAutor(autorEntidade);
        postEntidade.setConteudo(postRequest.getConteudo());
        postEntidade.setTitulo(postRequest.getTitulo());

    }

}
