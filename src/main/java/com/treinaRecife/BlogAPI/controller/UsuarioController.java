package com.treinaRecife.BlogAPI.controller;

import com.treinaRecife.BlogAPI.dto.request.AutenticacaoRequest;
import com.treinaRecife.BlogAPI.dto.request.RegistroRequest;
import com.treinaRecife.BlogAPI.dto.response.RegisterResponse;
import com.treinaRecife.BlogAPI.dto.response.TokenDTO;
import com.treinaRecife.BlogAPI.error.ErrorResponseForExceptions;
import com.treinaRecife.BlogAPI.error.ErrorResponseForValidations;
import com.treinaRecife.BlogAPI.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/autenticacao")
@RequiredArgsConstructor
@Tag(name = "Usuario EndPoints", description = "A area onde você realiza operações relacionadas ao Usuario")

public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Registra um Usuario no sistema")
    @ApiResponses(value =
            {@ApiResponse(
                    responseCode = "201",
                    description = "Retorna uma Mensagem com a Data de Criação",
                    content = {@Content(mediaType = "application/json",
                            schema =
                            @Schema(
                                    implementation = RegisterResponse.class))}),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Dados passados não sao validos",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ErrorResponseForValidations.class))}),
                    @ApiResponse(
                            responseCode = "400 -",
                            description = "Email já cadastrado",
                            content = {@Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseForExceptions.class))})})
    @PostMapping(value = "/registrar")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegistroRequest registroRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.registrarUsuario(registroRequest));
    }

    @Operation(summary = "Loga no sistema")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Loga com sucesso", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TokenDTO.class))}), @ApiResponse(responseCode = "400", description = "Email/Senha incorretos", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForExceptions.class))}), @ApiResponse(responseCode = "(400)", description = "Dados passados não sao validos", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseForValidations.class))})})
    @PostMapping(value = "/autenticar")
    public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid AutenticacaoRequest autenticacaoRequest) {
        return ResponseEntity.ok(usuarioService.autenticarUsuario(autenticacaoRequest));
    }


}
