package com.example.blogdbcompare.repository.neo4j;

import com.example.blogdbcompare.model.neo4j.InlinkNode;
import lombok.Data;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import java.util.List;
import java.util.Map;

public interface InlinkNodeRepository extends Neo4jRepository<InlinkNode, Long> {

    // Query 16: Find all inlinks pointing to posts by a specific author.
    @Query("MATCH (a:Author {`Author ID`: $authorId})<-[:authored_by]-(p:Post)<-[:links_to]-(i:Inlink) RETURN i")
    List<InlinkNode> findInlinksByAuthorId(Long authorId);
}
