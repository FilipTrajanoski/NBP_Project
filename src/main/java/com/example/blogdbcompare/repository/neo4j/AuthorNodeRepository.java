package com.example.blogdbcompare.repository.neo4j;

import com.example.blogdbcompare.model.neo4j.AuthorNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorNodeRepository extends Neo4jRepository<AuthorNode, Long> {

    // Query 5: Top 5 authors with most posts
    @Query("MATCH (a:Author)<-[:authored_by]-(p:Post) " +
            "RETURN a ORDER BY count(p) DESC LIMIT 5")
    List<AuthorNode> findTop5AuthorsByPosts();

    // Query 10: Authors with posts having more than N inlinks
    @Query("MATCH (a:Author)<-[:authored_by]-(p:Post) " +
            "WHERE p.`Number of retrieved inlinks` > $minInlinks " +
            "RETURN DISTINCT a")
    List<AuthorNode> findAuthorsWithPostsHavingInlinksGreaterThan(int minInlinks);
}
