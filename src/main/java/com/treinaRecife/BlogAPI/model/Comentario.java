package com.treinaRecife.BlogAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_comentarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComentario;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String texto;

    @CreationTimestamp
    private LocalDateTime dataDeCriacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idAutor")
    private Autor autor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idPost")
    private Post post;

    @Builder
    public Comentario(String texto, Autor autor, Post post) {
        this.texto = texto;
        this.autor = autor;
        this.post = post;
    }


}
