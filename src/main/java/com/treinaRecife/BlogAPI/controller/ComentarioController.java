package com.treinaRecife.BlogAPI.controller;

import com.treinaRecife.BlogAPI.dto.request.ComentarioRequest;
import com.treinaRecife.BlogAPI.dto.response.ComentarioResponse;
import com.treinaRecife.BlogAPI.service.ComentarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/comentarios")
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioService comentarioService;


    @GetMapping(value = "/{idPost}")
    public ResponseEntity<Page<ComentarioResponse>> acharComentariosByPostId(@PathVariable Long idPost, Pageable pageable) {
        return ResponseEntity.ok(comentarioService.paginarComentariosByPostId(idPost, pageable));
    }

    @PutMapping(value = "{idComentario}")
    public ResponseEntity<ComentarioResponse> atualizarComentarioByComentarioId(@PathVariable Long idComentario, @RequestBody @Valid ComentarioRequest comentarioRequest) {
        return ResponseEntity.ok(comentarioService.atualizarComentarioByIdComentario(idComentario, comentarioRequest));
    }

    @GetMapping
    public ResponseEntity<Page<ComentarioResponse>> paginarComentarios(Pageable pageable) {
        return ResponseEntity.ok(comentarioService.paginarComentarios(pageable));
    }

    @DeleteMapping(value = "{idComentario}")
    public ResponseEntity<Void> deletarComentarioById(@PathVariable Long idComentario) {
        comentarioService.deletarComentario(idComentario);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
