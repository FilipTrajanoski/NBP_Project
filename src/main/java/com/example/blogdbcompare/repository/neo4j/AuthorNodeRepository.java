package com.example.blogdbcompare.repository.neo4j;

import com.example.blogdbcompare.model.neo4j.AuthorNode;
import com.example.blogdbcompare.model.neo4j.PostNode;
import lombok.Data;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

record AuthorWithAveragePostLengthDto(AuthorNode author, Double averagePostLength) {}
record AuthorEngagementMetricsDto(Long authorId, String authorName, Double averageNumComments, Integer sumNumRetrievedInlinks) {}
record AuthorStatsDto(String authorName, Double averageWordLength, Double averageMeibiScore) {}
record AuthorWithAverageCommentsDto(AuthorNode author, Double avgComments) {}

@Repository
public interface AuthorNodeRepository extends Neo4jRepository<AuthorNode, Long> {

    // Query 8: Top 5 authors with most posts
    @Query("MATCH (a:Author)<-[:authored_by]-(p:Post) " +
            "RETURN a, count(p) AS postCount " +
            "ORDER BY postCount DESC " +
            "LIMIT 5")
    List<AuthorNode> findTop5AuthorsByPosts();

    // Query 9: Authors with posts having more than N inlinks
    @Query("MATCH (a:Author)<-[:authored_by]-(p:Post) " +
            "WHERE p.`Number of retrieved inlinks` > $minInlinks " +
            "RETURN DISTINCT a")
    List<AuthorNode> findAuthorsWithPostsHavingInlinksGreaterThan(int minInlinks);

    // Query 10: Find Authors with Their Average Post Length
    @Query("MATCH (a:Author)<-[:authored_by]-(p:Post) RETURN a AS author, AVG(p.`Post Length (#words)`) AS averagePostLength")
    List<AuthorWithAveragePostLengthDto> findAuthorsWithAvgPostLength();

    // Query 11: Author Engagement Metrics
    @Query("MATCH (a:Author)<-[:authored_by]-(p:Post) " +
            "RETURN a.`Author ID` AS authorId, a.Name as authorName, AVG(p.`Number of comments`) as averageNumComments, SUM(p.`Number of retrieved inlinks`) AS sumNumRetrievedInlinks")
    List<AuthorEngagementMetricsDto> findAuthorEngagementMetrics();

    // Query 12: Average word length and MEIBI score by author.
    @Query("MATCH (a:Author)<-[:authored_by]-(p:Post) RETURN a.Name as authorName, AVG(p.`Average word length (characters)`) as averageWordLength, AVG(p.`MEIBI score`) as averageMeibiScore")
    List<AuthorStatsDto> getAuthorStats();

    // Query 13: Top 5 authors with the highest average number of comments per post
    @Query("MATCH (a:Author)<-[:authored_by]-(p:Post) " +
            "RETURN a AS author, AVG(p.`Number of comments`) as avgComments " +
            "ORDER BY avgComments DESC " +
            "LIMIT 5")
    List<AuthorWithAverageCommentsDto> findTopAuthorsByAvgCommentsPerPost();
}
