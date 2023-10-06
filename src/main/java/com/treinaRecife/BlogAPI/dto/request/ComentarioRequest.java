package com.treinaRecife.BlogAPI.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ComentarioRequest {


    @NotBlank(message = "Campo obrigatorio")
    private String texto;

    @NotNull(message = "Campo obrigatorio")
    @Min(value = 1, message = "idAutor não pode ser um valor abaixo de 1")
    private Long idAutor;

    @NotNull(message = "Campo obrigatorio")
    @Min(value = 1, message = "idPost não pode ser um valor abaixo de 1")
    private Long idPost;
}
