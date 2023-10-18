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

    @Embedded
    private Endereco endereco;

    @Builder
    public Autor(String nome, String sobreNome) {
        this.nome = nome;
        this.sobreNome = sobreNome;

    }
}
