package com.treinaRecife.BlogAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

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

    @ManyToOne()
    @JoinColumn(name = "idAutor")
    private Autor autor;

    @OneToMany(mappedBy = "post")
    private List<Comentario> comentarios;

    @Builder
    public Post(String titulo, String conteudo, Autor autor) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.autor = autor;
    }


}
