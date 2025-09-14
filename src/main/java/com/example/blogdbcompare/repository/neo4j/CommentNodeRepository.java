package com.example.blogdbcompare.repository.neo4j;

import com.example.blogdbcompare.model.neo4j.CommentNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import java.util.List;

public interface CommentNodeRepository extends Neo4jRepository<CommentNode, Long> {

    // Query 14: Comments for a post
    @Query("MATCH (p:Post {`Post ID`: $postId})-[:has]->(c:Comment) RETURN c")
    List<CommentNode> findByPostId(Long postId);

    // Query 15: Commenters Who Also Have Posts
    @Query("MATCH (c:Comment), (p:Post) WHERE c.Author = p.`Blogger''s Name` RETURN DISTINCT c")
    List<CommentNode> findCommentsByPostAuthors();
}
