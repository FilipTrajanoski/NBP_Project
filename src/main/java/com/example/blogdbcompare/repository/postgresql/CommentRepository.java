package com.example.blogdbcompare.repository.postgresql;

import com.example.blogdbcompare.model.postgresql.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Query 4: Comments for a post
    List<Comment> findByPostPostId(Long postId);
}
