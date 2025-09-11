package com.example.blogdbcompare.repository.neo4j;

import com.example.blogdbcompare.model.neo4j.PostNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostNodeRepository extends Neo4jRepository<PostNode, Long> {
}
