package com.example.blogdbcompare.model.postgresql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "inlinks")
@Data
public class Inlink {
    @Id
    private Long inlinkId;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    private String title;
    private String author;
    private Date inlinkDate;
    private String url;
}
