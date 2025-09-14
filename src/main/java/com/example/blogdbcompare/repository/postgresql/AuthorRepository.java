package com.example.blogdbcompare.repository.postgresql;

import com.example.blogdbcompare.model.postgresql.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    // Query 8: Top 5 authors with most posts
    @Query("SELECT a FROM Author a JOIN Post p ON a.authorId = p.author.authorId " +
            "GROUP BY a ORDER BY COUNT(p) DESC")
    List<Author> findTop5AuthorsByPosts();

    // Query 9: Authors with posts having more than N inlinks
    @Query("SELECT DISTINCT a FROM Author a JOIN Post p ON a.authorId = p.author.authorId " +
            "WHERE p.numInlinks > :minInlinks")
    List<Author> findAuthorsWithPostsHavingInlinksGreaterThan(int minInlinks);

    // Query 10: Find Authors with Their Average Post Length
    @Query("SELECT a, AVG(p.postLengthWords) FROM Author a JOIN Post p ON a.authorId = p.author.authorId GROUP BY a.authorId")
    List<Object[]> findAuthorsWithAvgPostLength();

    // Query 11: Author Engagement Metrics
    @Query("SELECT a.authorId, a.name, AVG(p.numComments), SUM(p.numInlinks) " +
            "FROM Author a JOIN Post p ON a.authorId = p.author.authorId GROUP BY a.authorId, a.name")
    List<Object[]> findAuthorEngagementMetrics();

    // Query 12: Average word length and MEIBI score by author.
    @Query("SELECT a.name, AVG(p.avgWordLength), AVG(p.meibiScore) FROM Post p JOIN p.author a GROUP BY a.authorId")
    List<Object[]> getAuthorStats();

    // Query 13: Top 5 authors with the highest average number of comments per post
    @Query("SELECT a, AVG(p.numComments) as avgComments " +
            "FROM Author a " +
            "JOIN Post p ON p.author.authorId = a.authorId " +
            "GROUP BY a.authorId " +
            "ORDER BY avgComments DESC " +
            "LIMIT 5")
    List<Object[]> findTopAuthorsByAvgCommentsPerPost();
}
