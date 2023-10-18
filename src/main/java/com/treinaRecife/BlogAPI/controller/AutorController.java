package com.treinaRecife.BlogAPI.controller;

import com.treinaRecife.BlogAPI.dto.request.AutorRequest;
import com.treinaRecife.BlogAPI.dto.response.AutorResponse;
import com.treinaRecife.BlogAPI.error.ErrorResponseForExceptions;
import com.treinaRecife.BlogAPI.error.ErrorResponseForValidations;
import com.treinaRecife.BlogAPI.service.AutorService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/autores")
@RequiredArgsConstructor
@Tag(name = "Autor EndPoints", description = "A area onde você realiza operações relacionadas ao Autor")
public class AutorController {

    private final AutorService autorService;

    @Operation(summary = "Salva um Autor no banco de dados")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Retorna o Autor criado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AutorResponse.class))}), @ApiResponse(responseCode = "400", description = "Dados passados não sao validos", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForValidations.class))})})
    @PostMapping
    public ResponseEntity<AutorResponse> salvarUsuario(@RequestBody @Valid AutorRequest autorRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(autorService.salvarUsuario(autorRequest));
    }

    @Operation(summary = "Retorna uma Pagina com todos os Autores cadastrados")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorna a Pagina de AUTORES", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AutorResponse.class))}), @ApiResponse(responseCode = "200", description = "Pagina no momento encontra-se vazia", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForExceptions.class))})})
    @GetMapping
    public ResponseEntity<Page<AutorResponse>> paginarUsuarios(@Parameter(description = "Parametros de paginação") Pageable pageable) {
        return ResponseEntity.ok(autorService.paginarUsuarios(pageable));
    }

    @Operation(summary = "Retorna dados referente ao Autor dado seu Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorna dados do Autor", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AutorResponse.class))}), @ApiResponse(responseCode = "404", description = "Autor não encontrado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForExceptions.class))})})
    @GetMapping(value = "/{idAutor}")
    public ResponseEntity<AutorResponse> acharUsuarioPorId(@PathVariable Long idAutor) {
        return ResponseEntity.ok(autorService.acharUsuarioPorId(idAutor));
    }

    @Operation(summary = "Atualiza dados de um Autor dado seu Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorna o Autor atualizado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AutorResponse.class))}), @ApiResponse(responseCode = "404", description = "Autor não encontrado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForExceptions.class))}), @ApiResponse(responseCode = "400", description = "Dados passados não sao validos", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForValidations.class))})})
    @PutMapping(value = "/{idAutor}")
    public ResponseEntity<AutorResponse> atualizarAutorPorId(@PathVariable Long idAutor, @RequestBody @Valid AutorRequest autorRequest) {
        return ResponseEntity.ok(autorService.atualizarUsuarioPorId(idAutor, autorRequest));
    }

    @Operation(summary = "Deleta um Autor dado seu ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Deleta o Autor"), @ApiResponse(responseCode = "404", description = "Autor não encontrado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForExceptions.class))})})
    @DeleteMapping(value = "/{idAutor}")
    public ResponseEntity<Void> deletarAutorPorId(@PathVariable Long idAutor) {
        autorService.deletarUsuarioPorId(idAutor);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
