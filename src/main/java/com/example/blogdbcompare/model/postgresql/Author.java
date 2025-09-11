package com.example.blogdbcompare.model.postgresql;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "authors")
@Data
public class Author {
    @Id
    private Long authorId;
    private String name;
    private Integer meibi;
    private Integer meibix;
    private Double avgWords;
    private Double avgWordsNoStop;
}
