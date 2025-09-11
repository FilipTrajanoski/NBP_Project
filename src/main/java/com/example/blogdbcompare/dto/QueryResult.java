package com.example.blogdbcompare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QueryResult {
    private String queryName;
    private long executionTimeMs;
    private Object data;
    private String databaseType;
}
