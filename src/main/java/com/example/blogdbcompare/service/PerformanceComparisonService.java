package com.example.blogdbcompare.service;

import com.example.blogdbcompare.dto.PerformanceComparison;
import com.example.blogdbcompare.dto.QueryResult;
import com.example.blogdbcompare.repository.neo4j.AuthorNodeRepository;
import com.example.blogdbcompare.repository.neo4j.PostNodeRepository;
import com.example.blogdbcompare.repository.postgresql.AuthorRepository;
import com.example.blogdbcompare.repository.postgresql.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PerformanceComparisonService {

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;
    private final PostNodeRepository postNodeRepository;
    private final AuthorNodeRepository authorNodeRepository;

    public PerformanceComparisonService(PostRepository postRepository, AuthorRepository authorRepository, PostNodeRepository postNodeRepository, AuthorNodeRepository authorNodeRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
        this.postNodeRepository = postNodeRepository;
        this.authorNodeRepository = authorNodeRepository;
    }

    public QueryResult executePostgresQuery(int queryId, Map<String, Object> params) {
        long startTime = System.currentTimeMillis();
        Object result = null;

        switch (queryId) {
            case 1:
                result = postgresQuery1(params);
                break;
//            case 2:
//                result = postgresQuery2(params);
//                break;
            // Add cases for all 10 queries
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
//            case 2:
//                result = neo4jQuery2(params);
//                break;
            // Add cases for all 10 queries
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
}
