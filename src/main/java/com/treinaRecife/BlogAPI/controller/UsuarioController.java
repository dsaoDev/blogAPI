package com.treinaRecife.BlogAPI.controller;

import com.treinaRecife.BlogAPI.dto.request.UsuarioRequest;
import com.treinaRecife.BlogAPI.dto.response.UsuarioResponse;
import com.treinaRecife.BlogAPI.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/autores")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponse> salvarUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvarUsuario(usuarioRequest));
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioResponse>> paginarUsuarios(Pageable pageable) {
        return ResponseEntity.ok(usuarioService.paginarUsuarios(pageable));
    }

    @GetMapping(value = "/{idUsuario}")
    public ResponseEntity<UsuarioResponse> acharUsuarioPorId(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(usuarioService.acharUsuarioPorId(idUsuario));
    }

    @PutMapping(value = "/{idUsuario}")
    public ResponseEntity<UsuarioResponse> atualizarUsuarioPorId(@PathVariable Long idUsuario, @RequestBody @Valid UsuarioRequest usuarioRequest) {
        return ResponseEntity.ok(usuarioService.atualizarUsuarioPorId(idUsuario, usuarioRequest));
    }

    @DeleteMapping(value = "/{idUsuario}")
    public ResponseEntity<Void> deletarUsuarioPorId(@PathVariable Long idUsuario) {
        usuarioService.deletarUsuarioPorId(idUsuario);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
