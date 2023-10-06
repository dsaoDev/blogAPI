package com.treinaRecife.BlogAPI.controller;

import com.treinaRecife.BlogAPI.dto.response.ComentarioResponse;
import com.treinaRecife.BlogAPI.service.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/comentarios")
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioService comentarioService;


    @GetMapping(value = "/{idPost}")
    public ResponseEntity<Page<ComentarioResponse>> acharComentariosByPostId(@PathVariable Long idPost, Pageable pageable){
            return ResponseEntity.ok(comentarioService.paginarComentariosByPostId(idPost, pageable));
    }
}
