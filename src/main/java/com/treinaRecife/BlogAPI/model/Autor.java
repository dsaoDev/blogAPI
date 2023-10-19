package com.treinaRecife.BlogAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_autores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAutor;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 150, nullable = false)
    private String sobreNome;

    @Column(length = 14, nullable = false, unique = true)
    private String cpf;

    @Column(length = 150, nullable = false, unique = true)
    private String email;

    @Embedded
    private Endereco endereco;

    @Builder
    public Autor(String nome, String sobreNome, String cpf, String email) {
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.cpf = cpf;
        this.email = email;
    }
}
