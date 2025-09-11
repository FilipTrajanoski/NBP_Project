package com.example.blogdbcompare.repository.neo4j;

import com.example.blogdbcompare.model.neo4j.AuthorNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorNodeRepository extends Neo4jRepository<AuthorNode, Long> {
}
