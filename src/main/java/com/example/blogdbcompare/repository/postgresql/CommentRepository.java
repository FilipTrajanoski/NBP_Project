package com.example.blogdbcompare.repository.postgresql;

import com.example.blogdbcompare.model.postgresql.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Query 14: Comments for a post
    List<Comment> findByPostPostId(Long postId);

    // Query 15: Commenters Who Also Have Posts
    @Query("SELECT DISTINCT c FROM Comment c WHERE c.author IN (SELECT p.bloggerName FROM Post p)")
    List<Comment> findCommentsByPostAuthors();
}
