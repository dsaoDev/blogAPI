package com.treinaRecife.BlogAPI.repository;

import com.treinaRecife.BlogAPI.model.Comentario;
import com.treinaRecife.BlogAPI.model.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT p FROM Post p WHERE p.autor.idUsuario = :idAutor")
    Page<Post> findPostsByAutorId(Long idAutor, Pageable pageable);
}
