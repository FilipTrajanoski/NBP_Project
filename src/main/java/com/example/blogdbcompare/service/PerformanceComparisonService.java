package com.example.blogdbcompare.service;

import com.example.blogdbcompare.dto.PerformanceComparison;
import com.example.blogdbcompare.dto.QueryResult;
import com.example.blogdbcompare.repository.neo4j.AuthorNodeRepository;
import com.example.blogdbcompare.repository.neo4j.CommentNodeRepository;
import com.example.blogdbcompare.repository.neo4j.InlinkNodeRepository;
import com.example.blogdbcompare.repository.neo4j.PostNodeRepository;
import com.example.blogdbcompare.repository.postgresql.AuthorRepository;
import com.example.blogdbcompare.repository.postgresql.CommentRepository;
import com.example.blogdbcompare.repository.postgresql.InlinkRepository;
import com.example.blogdbcompare.repository.postgresql.PostRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

@Service
public class PerformanceComparisonService {

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;
    private final PostNodeRepository postNodeRepository;
    private final AuthorNodeRepository authorNodeRepository;
    private final CommentRepository commentRepository;
    private final CommentNodeRepository commentNodeRepository;
    private final InlinkRepository inlinkRepository;
    private final InlinkNodeRepository inlinkNodeRepository;

    public PerformanceComparisonService(PostRepository postRepository, AuthorRepository authorRepository, PostNodeRepository postNodeRepository, AuthorNodeRepository authorNodeRepository, CommentRepository commentRepository, CommentNodeRepository commentNodeRepository, InlinkRepository inlinkRepository, InlinkNodeRepository inlinkNodeRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
        this.postNodeRepository = postNodeRepository;
        this.authorNodeRepository = authorNodeRepository;
        this.commentRepository = commentRepository;
        this.commentNodeRepository = commentNodeRepository;
        this.inlinkRepository = inlinkRepository;
        this.inlinkNodeRepository = inlinkNodeRepository;
    }

    public QueryResult executePostgresQuery(int queryId, Map<String, Object> params) {
        long startTime = System.currentTimeMillis();
        Object result = null;

        switch (queryId) {
            case 1:
                result = postgresQuery1(params);
                break;
            case 2:
                result = postgresQuery2(params);
                break;
            case 3:
                result = postgresQuery3(params);
                break;
            case 4:
                result = postgresQuery4(params);
                break;
            case 5:
                result = postgresQuery5(params);
                break;
            case 6:
                result = postgresQuery6(params);
                break;
            case 7:
                result = postgresQuery7(params);
                break;
            case 8:
                result = postgresQuery8(params);
                break;
            case 9:
                result = postgresQuery9(params);
                break;
            case 10:
                result = postgresQuery10(params);
                break;
            case 11:
                result = postgresQuery11(params);
                break;
            case 12:
                result = postgresQuery12(params);
                break;
            case 13:
                result = postgresQuery13(params);
                break;
            case 14:
                result = postgresQuery14(params);
                break;
            case 15:
                result = postgresQuery15(params);
                break;
            case 16:
                result = postgresQuery16(params);
                break;
            default:
                throw new IllegalArgumentException("Invalid query ID");
        }

        long executionTime = System.currentTimeMillis() - startTime;

        return new QueryResult("Query " + queryId, executionTime, result, "PostgreSQL");
    }

    public QueryResult executeNeo4jQuery(int queryId, Map<String, Object> params) {
        long startTime = System.currentTimeMillis();
        Object result = null;

        switch (queryId) {
            case 1:
                result = neo4jQuery1(params);
                break;
            case 2:
                result = neo4jQuery2(params);
                break;
            case 3:
                result = neo4jQuery3(params);
                break;
            case 4:
                result = neo4jQuery4(params);
                break;
            case 5:
                result = neo4jQuery5(params);
                break;
            case 6:
                result = neo4jQuery6(params);
                break;
            case 7:
                result = neo4jQuery7(params);
                break;
            case 8:
                result = neo4jQuery8(params);
                break;
            case 9:
                result = neo4jQuery9(params);
                break;
            case 10:
                result = neo4jQuery10(params);
                break;
            case 11:
                result = neo4jQuery11(params);
                break;
            case 12:
                result = neo4jQuery12(params);
                break;
            case 13:
                result = neo4jQuery13(params);
                break;
            case 14:
                result = neo4jQuery14(params);
                break;
            case 15:
                result = neo4jQuery15(params);
                break;
            case 16:
                result = neo4jQuery16(params);
                break;
            default:
                throw new IllegalArgumentException("Invalid query ID");
        }

        long executionTime = System.currentTimeMillis() - startTime;

        return new QueryResult("Query " + queryId, executionTime, result, "Neo4j");
    }

    public PerformanceComparison comparePerformance(int queryId, Map<String, Object> params) {
        QueryResult postgresResult = executePostgresQuery(queryId, params);
        QueryResult neo4jResult = executeNeo4jQuery(queryId, params);

        String difference = calculateDifference(postgresResult.getExecutionTimeMs(),
                neo4jResult.getExecutionTimeMs());

        return new PerformanceComparison(postgresResult, neo4jResult, difference);
    }

    private String calculateDifference(long postgresTime, long neo4jTime) {
        if (postgresTime == 0) return "N/A";

        long difference = Math.abs(neo4jTime - postgresTime);
        return String.valueOf(difference);
    }

    private Object postgresQuery1(Map<String, Object> params) {
        Long authorId = (Long) params.get("authorId");
        return postRepository.findByAuthorAuthorId(authorId);
    }

    private Object neo4jQuery1(Map<String, Object> params) {
        Long authorId = (Long) params.get("authorId");
        return postNodeRepository.findByAuthorId(authorId);
    }

    private Object postgresQuery2(Map<String, Object> params) {
        Long authorId = (Long) params.get("authorId");
        return postRepository.countByAuthorAuthorId(authorId);
    }

    private Object neo4jQuery2(Map<String, Object> params) {
        Long authorId = (Long) params.get("authorId");
        return postNodeRepository.countByAuthorId(authorId);
    }

    private Object postgresQuery3(Map<String, Object> params) {
        return postRepository.findPostsWithCommentCount();
    }

    private Object neo4jQuery3(Map<String, Object> params) {
        return postNodeRepository.findPostsWithCommentCount();
    }

    private Object postgresQuery4(Map<String, Object> params) {
        Long authorId = (Long) params.get("authorId");
        return postRepository.findAvgPostLengthByAuthor(authorId);
    }

    private Object neo4jQuery4(Map<String, Object> params) {
        Long authorId = (Long) params.get("authorId");
        return postNodeRepository.findAvgPostLengthByAuthor(authorId);
    }

    private Object postgresQuery5(Map<String, Object> params) {
        Long authorId = (Long) params.get("authorId");
        return postRepository.findFirstByAuthorAuthorIdOrderByPostDateDesc(authorId);
    }

    private Object neo4jQuery5(Map<String, Object> params) {
        Long authorId = (Long) params.get("authorId");
        return postNodeRepository.findMostRecentPostByAuthor(authorId);
    }

    private Object postgresQuery6(Map<String, Object> params) {
        return postRepository.findPostsWithAboveAvgComments();
    }

    private Object neo4jQuery6(Map<String, Object> params) {
        return postNodeRepository.findPostsWithAboveAvgComments();
    }

    private Object postgresQuery7(Map<String, Object> params) {
        String dateStr = (String) params.get("date");
        Instant instant = Instant.parse(dateStr);
        Date date = Date.from(instant);
        return postRepository.findRecentPostsWithInlinks(date);
    }

    private Object neo4jQuery7(Map<String, Object> params) {
        String dateStr = (String) params.get("date");
        ZonedDateTime date = ZonedDateTime.parse(dateStr);
        return postNodeRepository.findRecentPostsWithInlinks(date);
    }

    private Object postgresQuery8(Map<String, Object> params) {
        return authorRepository.findTop5AuthorsByPosts();
    }

    private Object neo4jQuery8(Map<String, Object> params) {
        return authorNodeRepository.findTop5AuthorsByPosts();
    }

    private Object postgresQuery9(Map<String, Object> params) {
        int minInlinks = Integer.parseInt(params.get("minInlinks").toString());
        return authorRepository.findAuthorsWithPostsHavingInlinksGreaterThan(minInlinks);
    }

    private Object neo4jQuery9(Map<String, Object> params) {
        int minInlinks = Integer.parseInt(params.get("minInlinks").toString());
        return authorNodeRepository.findAuthorsWithPostsHavingInlinksGreaterThan(minInlinks);
    }

    private Object postgresQuery10(Map<String, Object> params) {
        return authorRepository.findAuthorsWithAvgPostLength();
    }

    private Object neo4jQuery10(Map<String, Object> params) {
        return authorNodeRepository.findAuthorsWithAvgPostLength();
    }

    private Object postgresQuery11(Map<String, Object> params) {
        return authorRepository.findAuthorEngagementMetrics();
    }

    private Object neo4jQuery11(Map<String, Object> params) {
        return authorNodeRepository.findAuthorEngagementMetrics();
    }


    private Object postgresQuery12(Map<String, Object> params) {
        return authorRepository.getAuthorStats();
    }

    private Object neo4jQuery12(Map<String, Object> params) {
        return authorNodeRepository.getAuthorStats();
    }

    private Object postgresQuery13(Map<String, Object> params) {
        return authorRepository.findTopAuthorsByAvgCommentsPerPost();
    }

    private Object neo4jQuery13(Map<String, Object> params) {
        return authorNodeRepository.findTopAuthorsByAvgCommentsPerPost();
    }

    private Object postgresQuery14(Map<String, Object> params) {
        Long postId = (Long) params.get("postId");
        return commentRepository.findByPostPostId(postId);
    }

    private Object neo4jQuery14(Map<String, Object> params) {
        Long postId = (Long) params.get("postId");
        return commentNodeRepository.findByPostId(postId);
    }

    private Object postgresQuery15(Map<String, Object> params) {
        return commentRepository.findCommentsByPostAuthors();
    }

    private Object neo4jQuery15(Map<String, Object> params) {
        return commentNodeRepository.findCommentsByPostAuthors();
    }

    private Object postgresQuery16(Map<String, Object> params) {
        Long authorId = (Long) params.get("authorId");
        return inlinkRepository.findInlinksByAuthorId(authorId);
    }

    private Object neo4jQuery16(Map<String, Object> params) {
        Long authorId = (Long) params.get("authorId");
        return inlinkNodeRepository.findInlinksByAuthorId(authorId);
    }
}
