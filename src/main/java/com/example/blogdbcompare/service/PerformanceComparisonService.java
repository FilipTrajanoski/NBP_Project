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

        double percentage = ((double) (neo4jTime - postgresTime) / postgresTime) * 100;
        return String.format("%.2f%%", percentage);
    }

    private Object postgresQuery1(Map<String, Object> params) {
        // Simple query: Get author by ID
        Long authorId = (Long) params.get("authorId");
        return authorRepository.findById(authorId).orElse(null);
    }

    private Object neo4jQuery1(Map<String, Object> params) {
        // Simple query: Get author by ID
        Long authorId = (Long) params.get("authorId");
        return authorNodeRepository.findById(authorId);
    }
    private Object postgresQuery2(Map<String, Object> params) {
        Long authorId = (Long) params.get("authorId");
        return postRepository.findByAuthorAuthorId(authorId);
    }

    private Object neo4jQuery2(Map<String, Object> params) {
        Long authorId = (Long) params.get("authorId");
        return postNodeRepository.findByAuthorId(authorId);
    }

    private Object postgresQuery3(Map<String, Object> params) {
        Long authorId = (Long) params.get("authorId");
        return postRepository.countByAuthorAuthorId(authorId);
    }

    private Object neo4jQuery3(Map<String, Object> params) {
        Long authorId = (Long) params.get("authorId");
        return postNodeRepository.countByAuthorId(authorId);
    }

    private Object postgresQuery4(Map<String, Object> params) {
        Long postId = (Long) params.get("postId");
        return commentRepository.findByPostPostId(postId);
    }

    private Object neo4jQuery4(Map<String, Object> params) {
        Long postId = (Long) params.get("postId");
        return commentNodeRepository.findByPostId(postId);
    }

    private Object postgresQuery5(Map<String, Object> params) {
        return authorRepository.findTop5AuthorsByPosts();
    }

    private Object neo4jQuery5(Map<String, Object> params) {
        return authorNodeRepository.findTop5AuthorsByPosts();
    }

    private Object postgresQuery6(Map<String, Object> params) {
        int minComments = (int) params.get("minComments");
        return postRepository.findByNumCommentsGreaterThan(minComments);
    }

    private Object neo4jQuery6(Map<String, Object> params) {
        int minComments = (int) params.get("minComments");
        return postNodeRepository.findPostsByMinComments(minComments);
    }

    private Object postgresQuery7(Map<String, Object> params) {
        Long postId = (Long) params.get("postId");
        return inlinkRepository.findByPostPostId(postId);
    }

    private Object neo4jQuery7(Map<String, Object> params) {
        Long postId = (Long) params.get("postId");
        return inlinkNodeRepository.findByPostId(postId);
    }

    private Object postgresQuery8(Map<String, Object> params) {
        Long authorId = (Long) params.get("authorId");
        return postRepository.findAvgPostLengthByAuthor(authorId);
    }

    private Object neo4jQuery8(Map<String, Object> params) {
        Long authorId = (Long) params.get("authorId");
        return postNodeRepository.findAvgPostLengthByAuthor(authorId);
    }

    private Object postgresQuery9(Map<String, Object> params) {
        Long authorId = (Long) params.get("authorId");
        return postRepository.findFirstByAuthorAuthorIdOrderByPostDateDesc(authorId);
    }

    private Object neo4jQuery9(Map<String, Object> params) {
        Long authorId = (Long) params.get("authorId");
        return postNodeRepository.findMostRecentPostByAuthor(authorId);
    }

    private Object postgresQuery10(Map<String, Object> params) {
        int minInlinks = (int) params.get("minInlinks");
        return authorRepository.findAuthorsWithPostsHavingInlinksGreaterThan(minInlinks);
    }

    private Object neo4jQuery10(Map<String, Object> params) {
        int minInlinks = (int) params.get("minInlinks");
        return authorNodeRepository.findAuthorsWithPostsHavingInlinksGreaterThan(minInlinks);
    }

}
