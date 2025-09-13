package com.example.blogdbcompare.repository.postgresql;

import com.example.blogdbcompare.model.neo4j.PostNode;
import com.example.blogdbcompare.model.postgresql.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    // Query 2: All posts by author
    List<Post> findByAuthorAuthorId(Long authorId);

    // Query 3: Count posts by author
    long countByAuthorAuthorId(Long authorId);

    // Query 6: Posts with more than N comments
    List<Post> findByNumCommentsGreaterThan(int minComments);

    // Query 8: Average post length
    @Query("SELECT AVG(p.postLengthWords) FROM Post p WHERE p.author.authorId = :authorId")
    Double findAvgPostLengthByAuthor(Long authorId);

    // Query 9: Most recent post
    Post findFirstByAuthorAuthorIdOrderByPostDateDesc(Long authorId);
}
