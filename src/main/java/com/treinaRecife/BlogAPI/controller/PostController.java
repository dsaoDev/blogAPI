package com.treinaRecife.BlogAPI.controller;

import com.treinaRecife.BlogAPI.dto.request.ComentarioRequest;
import com.treinaRecife.BlogAPI.dto.request.PostRequest;
import com.treinaRecife.BlogAPI.dto.response.ComentarioResponse;
import com.treinaRecife.BlogAPI.dto.response.PostResponse;
import com.treinaRecife.BlogAPI.dto.response.PostResponseComComentarios;
import com.treinaRecife.BlogAPI.model.Post;
import com.treinaRecife.BlogAPI.service.ComentarioService;
import com.treinaRecife.BlogAPI.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final ComentarioService comentarioService;

    @PostMapping
    public ResponseEntity<PostResponse> salvarPost(@RequestBody PostRequest postRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.salvarPost(postRequest));
    }

    @GetMapping
    public ResponseEntity<Page<PostResponse>> paginarPosts (Pageable pageable){
        return ResponseEntity.ok(postService.paginarPosts(pageable));
    }

    @GetMapping(value = "/{idPost}")
    public ResponseEntity<PostResponse> acharPostPorId (@PathVariable Long idPost){
        return ResponseEntity.ok(postService.acharPostPorId(idPost));
    }

    @PutMapping(value = "/{idPost}")
    public ResponseEntity<PostResponse> atualizarPostPorId(@PathVariable Long idPost, @RequestBody PostRequest postRequest){
        return ResponseEntity.ok(postService.atualizarPostPorId(idPost, postRequest));
    }

    @DeleteMapping(value = "/{idPost}")
    public ResponseEntity<Void> deletarPostPorId (@PathVariable Long idPost){
        postService.deletarPostPorId(idPost);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping(value = "/{idPost}/comentarios")
    public ResponseEntity<ComentarioResponse> salvarComentariosToPost(@PathVariable Long idPost, @RequestBody ComentarioRequest comentarioRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioService.salvarComentariosToPostPeloIdPost(idPost,comentarioRequest));
    }


}
