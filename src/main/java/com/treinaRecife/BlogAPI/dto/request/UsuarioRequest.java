package com.treinaRecife.BlogAPI.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsuarioRequest {

    @NotBlank(message = "Campo obrigatorio")
    private String nome;

    @NotBlank(message = "Campo obrigatorio")
    private String sobreNome;

    @NotBlank(message = "Campo obrigatorio")
    @Email(message = "Email digitado está invalido")
    private String email;

    @NotBlank(message = "Campo obrigatorio")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$" , message = "Senha deve conter no mínimo 8 caracteres, no mínimo uma letra maiúscula, uma minúscula, um número e caracter especial")
    private String senha;

    @NotBlank(message = "CEP é obrigatorio")
    @Size(max = 9, message = "CEP tem que ter no maximo {max} characters com hífen")
    private String cep;

    private String complemento;
}
