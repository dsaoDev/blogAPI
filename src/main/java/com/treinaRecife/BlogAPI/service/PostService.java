package com.treinaRecife.BlogAPI.service;

import com.treinaRecife.BlogAPI.dto.request.PostRequest;
import com.treinaRecife.BlogAPI.dto.response.PostResponse;
import com.treinaRecife.BlogAPI.exceptions.PostNotFoundException;
import com.treinaRecife.BlogAPI.mapper.PostMapper;
import com.treinaRecife.BlogAPI.model.Post;
import com.treinaRecife.BlogAPI.repository.PostRepository;
import lombok.RequiredArgsConstructor;
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











    //Metodo auxiliar
    private Post returnPost(Long idPost){
        return postRepository.findById(idPost).orElseThrow
                (() -> new PostNotFoundException("Post com id " + idPost + " Não encontrado"));
    }

}
