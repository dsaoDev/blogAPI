package com.treinaRecife.BlogAPI.repository;

import com.treinaRecife.BlogAPI.model.Comentario;
import com.treinaRecife.BlogAPI.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {


}
