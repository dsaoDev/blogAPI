package com.treinaRecife.BlogAPI.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegistroRequest {

    @NotBlank(message = "Campo obrigatorio")
    private String nome;

    @NotBlank(message = "Campo obrigaotrio")
    @Email(message = "Email invalido")
    private String email;

    @NotBlank(message = "Campo obrigatorio")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$"
            , message = "Senha deve conter no mínimo 8 caracteres" +
            ", no mínimo uma letra maiúscula, uma minúscula, um número e caracter especial")
    private String senha;
}
