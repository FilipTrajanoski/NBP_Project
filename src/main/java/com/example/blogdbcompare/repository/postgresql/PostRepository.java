package com.example.blogdbcompare.repository.postgresql;

import com.example.blogdbcompare.model.postgresql.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
