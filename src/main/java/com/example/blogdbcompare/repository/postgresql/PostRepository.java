package com.example.blogdbcompare.repository.postgresql;

import com.example.blogdbcompare.model.neo4j.PostNode;
import com.example.blogdbcompare.model.postgresql.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    // Query 1: All posts by author
    List<Post> findByAuthorAuthorId(Long authorId);

    // Query 2: Count posts by author
    long countByAuthorAuthorId(Long authorId);

    // Query 3: Find Posts with Their Comments Count
    @Query("SELECT p, COUNT(c) FROM Post p LEFT JOIN Comment c ON p.postId = c.post.postId GROUP BY p.postId")
    List<Object[]> findPostsWithCommentCount();

    // Query 4: Average post length
    @Query("SELECT AVG(p.postLengthWords) FROM Post p WHERE p.author.authorId = :authorId")
    Double findAvgPostLengthByAuthor(Long authorId);

    // Query 5: Most recent post
    Post findFirstByAuthorAuthorIdOrderByPostDateDesc(Long authorId);

    // Query 6: Posts with Above-Average Comment Count
    @Query("SELECT p FROM Post p WHERE p.numComments > (SELECT AVG(numComments) FROM Post)")
    List<Post> findPostsWithAboveAvgComments();

    // Query 7: Recent Posts with Inlinks
    @Query("SELECT p FROM Post p WHERE p.postDate > :date AND EXISTS (SELECT 1 FROM Inlink i WHERE i.post.postId = p.postId)")
    List<Post> findRecentPostsWithInlinks(Date date);
}
