package com.treinaRecife.BlogAPI.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequest {

    @NotBlank(message = "Campo obrigatorio")
    private String titulo;


    private String conteudo;

    @Min(value = 1, message = "Valor aceitado é de no minimo 1")
    @Positive(message = "Apenas números positivos são permitidos")
    private Long idAutor;
}
