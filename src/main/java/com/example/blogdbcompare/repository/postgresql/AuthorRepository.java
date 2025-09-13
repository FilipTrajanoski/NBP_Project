package com.example.blogdbcompare.repository.postgresql;

import com.example.blogdbcompare.model.postgresql.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    // Query 5: Top 5 authors with most posts
    @Query("SELECT a FROM Author a JOIN Post p ON a.authorId = p.author.authorId " +
            "GROUP BY a ORDER BY COUNT(p) DESC")
    List<Author> findTop5AuthorsByPosts();

    // Query 10: Authors with posts having more than N inlinks
    @Query("SELECT DISTINCT a FROM Author a JOIN Post p ON a.authorId = p.author.authorId " +
            "WHERE p.numInlinks > :minInlinks")
    List<Author> findAuthorsWithPostsHavingInlinksGreaterThan(int minInlinks);
}
