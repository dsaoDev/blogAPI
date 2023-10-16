package com.treinaRecife.BlogAPI.controller;

import com.treinaRecife.BlogAPI.dto.request.ComentarioRequest;
import com.treinaRecife.BlogAPI.dto.response.ComentarioResponse;
import com.treinaRecife.BlogAPI.dto.response.PostResponse;
import com.treinaRecife.BlogAPI.error.ErrorResponseForExceptions;
import com.treinaRecife.BlogAPI.error.ErrorResponseForValidations;
import com.treinaRecife.BlogAPI.service.ComentarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/comentarios")
@RequiredArgsConstructor
@Tag(name = "Comentario EndPoints", description = "A area onde você realiza operações relacionadas ao Comentario")
public class ComentarioController {

    private final ComentarioService comentarioService;


    @Operation(summary = "Retorna uma pagina com todos os Comentarios relacionados ao ID do post")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Pagina com Comentarios", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ComentarioResponse.class))}), @ApiResponse(responseCode = "200", description = "Pagina de Comentarios se encontra vazia", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForExceptions.class))})})
    @GetMapping(value = "/{idPost}")
    public ResponseEntity<Page<ComentarioResponse>> acharComentariosByPostId(@PathVariable Long idPost, @Parameter(description = "Parâmetros de paginação") Pageable pageable) {
        return ResponseEntity.ok(comentarioService.paginarComentariosByPostId(idPost, pageable));
    }

    @Operation(summary = "Atualiza um Comentario dado seu ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorna o Comentario atualizado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ComentarioResponse.class))}), @ApiResponse(responseCode = "404", description = "Comentario não encontrado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForExceptions.class))}), @ApiResponse(responseCode = "400", description = "Dados passados não sao validos", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForValidations.class))})})
    @PutMapping(value = "{idComentario}")
    public ResponseEntity<ComentarioResponse> atualizarComentarioByComentarioId(@PathVariable Long idComentario, @RequestBody @Valid ComentarioRequest comentarioRequest) {
        return ResponseEntity.ok(comentarioService.atualizarComentarioByIdComentario(idComentario, comentarioRequest));
    }

    @Operation(summary = "Retorna uma pagina com todos os Comentarios")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Pagina com Comentarios", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ComentarioResponse.class))}), @ApiResponse(responseCode = "200", description = "Pagina de Comentarios se encontra vazia", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForExceptions.class))})})
    @GetMapping
    public ResponseEntity<Page<ComentarioResponse>> paginarComentarios(@Parameter(description = "Parâmetros de paginação") Pageable pageable) {
        return ResponseEntity.ok(comentarioService.paginarComentarios(pageable));
    }

    @Operation(summary = "Deleta um Comentario dado seu ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Deleta o Comentario"), @ApiResponse(responseCode = "404", description = "Comentario não encontrado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForExceptions.class))})})
    @DeleteMapping(value = "{idComentario}")
    public ResponseEntity<Void> deletarComentarioById(@PathVariable Long idComentario) {
        comentarioService.deletarComentario(idComentario);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
