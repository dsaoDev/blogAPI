package com.treinaRecife.BlogAPI.repository;

import com.treinaRecife.BlogAPI.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
