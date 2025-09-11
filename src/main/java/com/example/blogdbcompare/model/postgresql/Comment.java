package com.example.blogdbcompare.model.postgresql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "comments")
@Data
public class Comment {
    @Id
    private Long commentId;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    private String content;
    private String author;
    private Date commentDate;
    private Integer vote;
}
