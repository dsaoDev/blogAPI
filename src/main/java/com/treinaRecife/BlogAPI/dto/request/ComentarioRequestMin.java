package com.treinaRecife.BlogAPI.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ComentarioRequestMin {

    @NotBlank(message = "Campo obrigatorio")
    private String texto;

    @NotNull(message = "Campo  obrigatorio")
    @Min(value = 1, message = "Valor aceitado é de no minimo 1")
    private Long idAutor;
}
