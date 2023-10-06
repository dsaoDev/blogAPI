package com.treinaRecife.BlogAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

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
    private LocalDate dataDeCriacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idUsuario")
    private Usuario autor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idPost")
    private Post post;


}
