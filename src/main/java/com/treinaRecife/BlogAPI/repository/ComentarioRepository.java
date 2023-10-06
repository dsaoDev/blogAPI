package com.treinaRecife.BlogAPI.repository;

import com.treinaRecife.BlogAPI.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario,Long> {
}
