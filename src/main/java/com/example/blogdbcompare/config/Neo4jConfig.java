package com.example.blogdbcompare.config;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.core.DatabaseSelection;
import org.springframework.data.neo4j.core.DatabaseSelectionProvider;
import org.springframework.data.neo4j.core.transaction.Neo4jTransactionManager;


@Configuration
public class Neo4jConfig {

    @Value("${spring.neo4j.uri}")
    private String neo4jUri;

    @Value("${spring.neo4j.authentication.username}")
    private String neo4jUsername;

    @Value("${spring.neo4j.authentication.password}")
    private String neo4jPassword;

    @Value("${spring.data.neo4j.database:blog-db}")
    private String databaseName;

    @Bean
    public Driver neo4jDriver() {
        return GraphDatabase.driver(
                neo4jUri,
                AuthTokens.basic(neo4jUsername, neo4jPassword)
        );
    }

    @Bean
    public DatabaseSelectionProvider databaseSelectionProvider() {
        return () -> DatabaseSelection.byName(databaseName);
    }

    @Bean
    public Neo4jTransactionManager transactionManager(Driver driver, DatabaseSelectionProvider databaseSelectionProvider) {
        return new Neo4jTransactionManager(driver, databaseSelectionProvider);
    }
}