package com.treinaRecife.BlogAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String conteudo;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime dataDeCriacao;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idUsuario")
    private Usuario autor;


    @Builder
    public Post(String titulo, String conteudo, Usuario autor) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.autor = autor;
    }


}
