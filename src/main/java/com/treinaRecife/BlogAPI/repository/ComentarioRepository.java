package com.treinaRecife.BlogAPI.repository;

import com.treinaRecife.BlogAPI.model.Comentario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario,Long> {

    @Query(value = "SELECT c FROM Comentario c WHERE c.post.idPost = :idPost")
    Page<Comentario> findComentarioByPostId(@Param(value = "idPost") Long idPost, Pageable pageable);
}
