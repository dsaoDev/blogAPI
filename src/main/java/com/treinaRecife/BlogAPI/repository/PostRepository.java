package com.treinaRecife.BlogAPI.repository;

import com.treinaRecife.BlogAPI.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
