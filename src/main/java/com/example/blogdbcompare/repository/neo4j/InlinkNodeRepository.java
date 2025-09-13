package com.example.blogdbcompare.repository.neo4j;

import com.example.blogdbcompare.model.neo4j.InlinkNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import java.util.List;

public interface InlinkNodeRepository extends Neo4jRepository<InlinkNode, Long> {

    // Query 7: Inlinks for a post
    @Query("MATCH (i:Inlink)-[:links_to]->(p:Post {`Post ID`: $postId}) RETURN i")
    List<InlinkNode> findByPostId(Long postId);
}
