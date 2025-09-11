package com.example.blogdbcompare.repository.postgresql;

import com.example.blogdbcompare.model.postgresql.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
