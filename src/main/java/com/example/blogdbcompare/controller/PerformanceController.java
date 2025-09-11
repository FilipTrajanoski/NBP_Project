package com.example.blogdbcompare.controller;

import com.example.blogdbcompare.dto.PerformanceComparison;
import com.example.blogdbcompare.dto.QueryResult;
import com.example.blogdbcompare.service.PerformanceComparisonService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/performance")
public class PerformanceController {

    private final PerformanceComparisonService performanceService;

    public PerformanceController(PerformanceComparisonService performanceService) {
        this.performanceService = performanceService;
    }

    @GetMapping("/query/{queryId}")
    public PerformanceComparison executeQuery(
            @PathVariable int queryId,
            @RequestParam Map<String, String> allParams) {

        Map<String, Object> params = convertParams(allParams);
        return performanceService.comparePerformance(queryId, params);
    }

    @GetMapping("/postgres/query/{queryId}")
    public QueryResult executePostgresQuery(
            @PathVariable int queryId,
            @RequestParam Map<String, String> allParams) {

        Map<String, Object> params = convertParams(allParams);
        return performanceService.executePostgresQuery(queryId, params);
    }

    @GetMapping("/neo4j/query/{queryId}")
    public QueryResult executeNeo4jQuery(
            @PathVariable int queryId,
            @RequestParam Map<String, String> allParams) {

        Map<String, Object> params = convertParams(allParams);
        return performanceService.executeNeo4jQuery(queryId, params);
    }

    @GetMapping("/all")
    public List<PerformanceComparison> executeAllQueries() {
        List<PerformanceComparison> results = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            results.add(performanceService.comparePerformance(i, new HashMap<>()));
        }

        return results;
    }

    private Map<String, Object> convertParams(Map<String, String> stringParams) {
        Map<String, Object> convertedParams = new HashMap<>();

        for (Map.Entry<String, String> entry : stringParams.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            try {
                if (value.matches("\\d+")) {
                    convertedParams.put(key, Long.parseLong(value));
                } else if (value.matches("\\d+\\.\\d+")) {
                    convertedParams.put(key, Double.parseDouble(value));
                } else {
                    convertedParams.put(key, value);
                }
            } catch (NumberFormatException e) {
                convertedParams.put(key, value);
            }
        }

        return convertedParams;
    }
}