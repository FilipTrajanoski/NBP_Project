package com.example.blogdbcompare.repository.neo4j;

import com.example.blogdbcompare.model.neo4j.PostNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostNodeRepository extends Neo4jRepository<PostNode, Long> {

    // Query 2: All posts by author
    @Query("MATCH (a:Author {`Author ID`: $authorId})<-[:authored_by]-(p:Post) RETURN p")
    List<PostNode> findByAuthorId(Long authorId);

    // Query 3: Count posts by author
    @Query("MATCH (a:Author {`Author ID`: $authorId})<-[:authored_by]-(p:Post) RETURN count(p)")
    long countByAuthorId(Long authorId);

    // Query 6: Posts with more than N comments
    @Query("MATCH (p:Post) WHERE p.`Number of comments` > $minComments RETURN p")
    List<PostNode> findPostsByMinComments(int minComments);

    // Query 8: Average post length (words)
    @Query("MATCH (a:Author {`Author ID`: $authorId})<-[:authored_by]-(p:Post) " +
            "RETURN avg(p.`Post Length (#words)`)")
    Double findAvgPostLengthByAuthor(Long authorId);

    // Query 9: Most recent post by author
    @Query("MATCH (a:Author {`Author ID`: $authorId})<-[:authored_by]-(p:Post) " +
            "RETURN p ORDER BY p.Date DESC LIMIT 1")
    PostNode findMostRecentPostByAuthor(Long authorId);
}
