package com.treinaRecife.BlogAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 150, nullable = false)
    private String sobreNome;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 20, nullable = false)
    private String senha;

    @Embedded
    private Endereco endereco;
    @Builder
    public Usuario(String nome, String sobreNome, String email, String senha){
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.email = email;
        this.senha = senha;
    }
}
