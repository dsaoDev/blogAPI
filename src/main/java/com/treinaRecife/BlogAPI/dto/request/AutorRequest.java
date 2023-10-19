package com.treinaRecife.BlogAPI.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@NoArgsConstructor
public class AutorRequest {

    @NotBlank(message = "Campo obrigatorio")
    private String nome;

    @NotBlank(message = "Campo obrigatorio")
    private String sobreNome;

    @NotBlank(message = "CEP é obrigatorio")
    @Size(max = 9, message = "CEP tem que ter no maximo {max} characters com hífen")
    private String cep;

    @NotBlank(message = "Campo obrigatorio")
    @CPF(message = "CPF invalido")
    private String cpf;

    @NotBlank(message = "Campo obrigatorio")
    @Email(message = "Email digitado está invalido")
    private String email;

    private String complemento;
}
