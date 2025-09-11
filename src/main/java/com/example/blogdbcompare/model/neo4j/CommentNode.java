package com.example.blogdbcompare.model.neo4j;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.ZonedDateTime;

@Node("Comment")
@Data
public class CommentNode {
    @Id
    @Property(name = "Comment ID")
    private Long commentId;
    @Relationship(type = "has", direction = Relationship.Direction.INCOMING)
    @Property(name = "Post ID")
    private PostNode post;
    @Property(name = "Content")
    private String content;
    @Property(name = "Author")
    private String author;
    @Property(name = "Date")
    private ZonedDateTime date;
    @Property(name = "Vote")
    private Boolean vote;
}
