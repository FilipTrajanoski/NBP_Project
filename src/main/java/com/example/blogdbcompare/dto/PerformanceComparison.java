package com.example.blogdbcompare.dto;

import lombok.Data;

@Data
public class PerformanceComparison {
    private QueryResult postgresResult;
    private QueryResult neo4jResult;
    private String performanceDifference;

    public PerformanceComparison(QueryResult postgresResult, QueryResult neo4jResult, String performanceDifference) {
        this.postgresResult = postgresResult;
        this.neo4jResult = neo4jResult;
        this.performanceDifference = performanceDifference;
    }
}
