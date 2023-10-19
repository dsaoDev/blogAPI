package com.treinaRecife.BlogAPI.repository;

import com.treinaRecife.BlogAPI.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {

    Optional<Autor> findByEmail(String email);
    Optional<Autor> findByCpf(String cpf);
}
