package com.example.blogdbcompare.repository.postgresql;

import com.example.blogdbcompare.model.postgresql.Inlink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

    public interface InlinkRepository extends JpaRepository<Inlink, Long> {

    // Query 16: Find all inlinks pointing to posts by a specific author.
    @Query("SELECT i FROM Inlink i WHERE i.post.author.authorId = :authorId")
    List<Inlink> findInlinksByAuthorId(Long authorId);
}
