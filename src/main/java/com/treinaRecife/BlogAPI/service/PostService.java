package com.treinaRecife.BlogAPI.service;

import com.treinaRecife.BlogAPI.dto.request.PostRequest;
import com.treinaRecife.BlogAPI.dto.response.PostResponse;
import com.treinaRecife.BlogAPI.mapper.PostMapper;
import com.treinaRecife.BlogAPI.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;

    private final PostRepository postRepository;
    public PostResponse salvarPost (PostRequest postRequest){
        var postEntidade = postMapper.dePostRequestParaEntidadePost(postRequest);

        postRepository.save(postEntidade);

        return postMapper.dePostEntidadeParaPostResponse(postEntidade);
    }

}
