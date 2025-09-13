package com.example.blogdbcompare.repository.postgresql;

import com.example.blogdbcompare.model.postgresql.Inlink;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InlinkRepository extends JpaRepository<Inlink, Long> {
    // Query 7: Inlinks for a post
    List<Inlink> findByPostPostId(Long postId);
}
