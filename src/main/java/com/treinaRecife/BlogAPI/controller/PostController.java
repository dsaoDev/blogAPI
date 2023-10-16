package com.treinaRecife.BlogAPI.controller;

import com.treinaRecife.BlogAPI.dto.request.ComentarioRequestMin;
import com.treinaRecife.BlogAPI.dto.request.PostRequest;
import com.treinaRecife.BlogAPI.dto.response.ComentarioResponse;
import com.treinaRecife.BlogAPI.dto.response.PostResponse;
import com.treinaRecife.BlogAPI.dto.response.PostResponseWithComments;
import com.treinaRecife.BlogAPI.error.ErrorResponseForExceptions;
import com.treinaRecife.BlogAPI.error.ErrorResponseForValidations;
import com.treinaRecife.BlogAPI.model.Post;
import com.treinaRecife.BlogAPI.service.ComentarioService;
import com.treinaRecife.BlogAPI.service.PostService;
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
@RequestMapping(value = "/posts")
@RequiredArgsConstructor
@Tag(name = "Post EndPoints", description = "A area onde você realiza operações relacionadas ao Post")
public class PostController {

    private final PostService postService;

    private final ComentarioService comentarioService;

    @Operation(summary = "Salva um POST no banco de dados")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Retorna o POST criado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PostResponse.class))}), @ApiResponse(responseCode = "400", description = "Dados passados não sao validos", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForValidations.class))})})
    @PostMapping
    public ResponseEntity<PostResponse> salvarPost(@RequestBody @Valid PostRequest postRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.salvarPost(postRequest));
    }

    @Operation(summary = "Retorna uma pagina com todos os POSTS")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Pagina com POSTS", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PostResponse.class))}), @ApiResponse(responseCode = "200", description = "Pagina de Posts se encontra vazia", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForExceptions.class))})})
    @GetMapping
    public ResponseEntity<Page<PostResponse>> paginarPosts(@Parameter(description = "Parâmetros de paginação") Pageable pageable) {
        return ResponseEntity.ok(postService.paginarPosts(pageable));
    }


    @Operation(summary = "Retorna um Post dado seu Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorna o Post", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PostResponse.class))}), @ApiResponse(responseCode = "404", description = "Post não encontrado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForExceptions.class))})})
    @GetMapping(value = "/{idPost}")
    public ResponseEntity<PostResponse> acharPostPorId(@PathVariable Long idPost) {
        return ResponseEntity.ok(postService.acharPostPorId(idPost));
    }

    @Operation(summary = "Atualiza um Post dado seu Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorna o Post atualizado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PostResponse.class))}), @ApiResponse(responseCode = "404", description = "Post não encontrado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForExceptions.class))}), @ApiResponse(responseCode = "400", description = "Dados passados não sao validos", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForValidations.class))})})
    @PutMapping(value = "/{idPost}")
    public ResponseEntity<PostResponse> atualizarPostPorId(@PathVariable Long idPost, @RequestBody @Valid PostRequest postRequest) {
        return ResponseEntity.ok(postService.atualizarPostPorId(idPost, postRequest));
    }

    @Operation(summary = "Deleta um Post dado seu ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Deleta o post"), @ApiResponse(responseCode = "404", description = "Post não encontrado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForExceptions.class))})})
    @DeleteMapping(value = "/{idPost}")
    public ResponseEntity<Void> deletarPostPorId(@PathVariable Long idPost) {
        postService.deletarPostPorId(idPost);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Retorna uma Pagina de  Posts dado ID do autor que o criou")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorna a Pagina de POSTS", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PostResponse.class))}), @ApiResponse(responseCode = "404", description = "Autor nao encontrado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForExceptions.class))})})
    @GetMapping(value = "/{idAutor}/autores")
    public ResponseEntity<Page<PostResponse>> buscarPostPorIdAutor(@PathVariable Long idAutor, @Parameter(description = "Parâmetros de paginação") Pageable pageable) {
        return ResponseEntity.ok(postService.acharPostByAutorId(idAutor, pageable));
    }

    @Operation(summary = "Salva um comentario a o POST dado id do Post")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorna o comentario criado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ComentarioResponse.class))}), @ApiResponse(responseCode = "404", description = "Post não encontrado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForExceptions.class))}), @ApiResponse(responseCode = "400", description = "Dados passados não sao validos", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForValidations.class))})})
    @PostMapping(value = "/{idPost}/comentarios")
    public ResponseEntity<ComentarioResponse> salvarComentariosToPost(@PathVariable Long idPost, @RequestBody @Valid ComentarioRequestMin comentarioRequestMin) {
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioService.salvarComentariosToPostPeloIdPost(idPost, comentarioRequestMin));
    }

    @Operation(summary = "Retorna um Post com todos os comentarios relacionado a ele")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorna o POST com seus comentarios", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PostResponseWithComments.class))}), @ApiResponse(responseCode = "404", description = "Post não encontrado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForExceptions.class))})})
    @GetMapping(value = "/{idPost}/postsEComentarios")
    public ResponseEntity<PostResponseWithComments> buscarPostComComentarios(@PathVariable Long idPost) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.retornarPostComComentarios(idPost));
    }


}
