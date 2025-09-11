package com.example.blogdbcompare.model.postgresql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "posts")
@Data
public class Post {
    @Id
    private Long postId;
    private String title;
    private String bloggerName;
    @ManyToOne
    @JoinColumn(name = "blogger_id")
    private Author author;
    private Integer numComments;
    private String content;
    private String url;
    private Date postDate;
    private Integer numInlinks;
    private Integer numRetrievedComments;
    private Integer postLengthWords;
    private Integer postLengthNoStop;
    private Double avgWordLength;
    private Double avgWordLengthNoStop;
    private Double meibiScore;
    private Double meibixScore;
}
