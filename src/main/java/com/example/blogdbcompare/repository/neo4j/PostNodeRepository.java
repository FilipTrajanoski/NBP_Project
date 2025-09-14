package com.example.blogdbcompare.repository.neo4j;

import com.example.blogdbcompare.model.neo4j.PostNode;
import lombok.Data;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

record PostWithCommentCountDto(PostNode post, Long commentCount) {}

@Repository
public interface PostNodeRepository extends Neo4jRepository<PostNode, Long> {

    // Query 1: All posts by author
    @Query("MATCH (a:Author {`Author ID`: $authorId})<-[:authored_by]-(p:Post) RETURN p")
    List<PostNode> findByAuthorId(Long authorId);

    // Query 2: Count posts by author
    @Query("MATCH (a:Author {`Author ID`: $authorId})<-[:authored_by]-(p:Post) RETURN count(p)")
    long countByAuthorId(Long authorId);

    // Query 3: Find Posts with Their Comments Count
    @Query("MATCH (p:Post) OPTIONAL MATCH (p)<-[:has]-(c:Comment) RETURN p AS post, COUNT(c) AS commentCount")
    List<PostWithCommentCountDto> findPostsWithCommentCount();

    // Query 4: Average post length (words)
    @Query("MATCH (a:Author {`Author ID`: $authorId})<-[:authored_by]-(p:Post) " +
            "RETURN avg(p.`Post Length (#words)`)")
    Double findAvgPostLengthByAuthor(Long authorId);

    // Query 5: Most recent post by author
    @Query("MATCH (a:Author {`Author ID`: $authorId})<-[:authored_by]-(p:Post) " +
            "RETURN p ORDER BY p.Date DESC LIMIT 1")
    PostNode findMostRecentPostByAuthor(Long authorId);

    // Query 6: Posts with Above-Average Comment Count
    @Query("""
       MATCH (p:Post)
       WITH AVG(p.`Number of comments`) AS avgComments
       MATCH (p2:Post)
       WHERE p2.`Number of comments` > avgComments
       RETURN p2
       """)
    List<PostNode> findPostsWithAboveAvgComments();

    // Query 7: Recent Posts with Inlinks
    @Query("MATCH (p:Post) WHERE p.Date > $date AND EXISTS ((p)<-[:links_to]-(:Inlink)) RETURN p")
    List<PostNode> findRecentPostsWithInlinks(ZonedDateTime date);
}
