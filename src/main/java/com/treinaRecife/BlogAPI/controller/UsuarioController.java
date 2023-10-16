package com.treinaRecife.BlogAPI.controller;

import com.treinaRecife.BlogAPI.dto.request.UsuarioRequest;
import com.treinaRecife.BlogAPI.dto.response.PostResponse;
import com.treinaRecife.BlogAPI.dto.response.UsuarioResponse;
import com.treinaRecife.BlogAPI.error.ErrorResponseForExceptions;
import com.treinaRecife.BlogAPI.error.ErrorResponseForValidations;
import com.treinaRecife.BlogAPI.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/autores")
@RequiredArgsConstructor
@Tag(name = "Autor EndPoints", description = "A area onde você realiza operações relacionadas ao Autor")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Salva um Autor no banco de dados")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Retorna o Autor criado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class))}), @ApiResponse(responseCode = "400", description = "Dados passados não sao validos", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForValidations.class))})})
    @PostMapping
    public ResponseEntity<UsuarioResponse> salvarUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvarUsuario(usuarioRequest));
    }

    @Operation(summary = "Retorna uma Pagina com todos os Autores cadastrados")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorna a Pagina de AUTORES", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class))}), @ApiResponse(responseCode = "200", description = "Pagina no momento encontra-se vazia", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForExceptions.class))})})
    @GetMapping
    public ResponseEntity<Page<UsuarioResponse>> paginarUsuarios(@Parameter(description = "Parametros de paginação") Pageable pageable) {
        return ResponseEntity.ok(usuarioService.paginarUsuarios(pageable));
    }

    @Operation(summary = "Retorna dados referente ao Autor dado seu Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorna dados do Autor", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class))}), @ApiResponse(responseCode = "404", description = "Autor não encontrado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForExceptions.class))})})
    @GetMapping(value = "/{idUsuario}")
    public ResponseEntity<UsuarioResponse> acharUsuarioPorId(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(usuarioService.acharUsuarioPorId(idUsuario));
    }

    @Operation(summary = "Atualiza dados de um Autor dado seu Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorna o Autor atualizado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class))}), @ApiResponse(responseCode = "404", description = "Autor não encontrado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForExceptions.class))}), @ApiResponse(responseCode = "400", description = "Dados passados não sao validos", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForValidations.class))})})
    @PutMapping(value = "/{idUsuario}")
    public ResponseEntity<UsuarioResponse> atualizarUsuarioPorId(@PathVariable Long idUsuario, @RequestBody @Valid UsuarioRequest usuarioRequest) {
        return ResponseEntity.ok(usuarioService.atualizarUsuarioPorId(idUsuario, usuarioRequest));
    }

    @Operation(summary = "Deleta um Autor dado seu ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Deleta o Autor"), @ApiResponse(responseCode = "404", description = "Autor não encontrado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForExceptions.class))})})
    @DeleteMapping(value = "/{idUsuario}")
    public ResponseEntity<Void> deletarUsuarioPorId(@PathVariable Long idUsuario) {
        usuarioService.deletarUsuarioPorId(idUsuario);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
