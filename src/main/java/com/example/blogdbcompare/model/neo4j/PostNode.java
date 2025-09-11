package com.example.blogdbcompare.model.neo4j;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.ZonedDateTime;

@Node("Post")
@Data
public class PostNode {
    @Id
    @Property(name = "Post ID")
    private Long postId;
    @Property(name = "Title")
    private String title;
    @Property(name = "Blogger's Name")
    private String bloggerName;
    @Relationship(type = "authored_by", direction = Relationship.Direction.OUTGOING)
    @Property(name = "Blogger's ID")
    private AuthorNode author;
    @Property(name = "Number of comments")
    private Integer numComments;
    @Property(name = "Content")
    private String content;
    @Property(name = "URL")
    private String url;
    @Property(name = "Date")
    private ZonedDateTime postDate;
    @Property(name = "Number of retrieved inlinks")
    private Integer numInlinks;
    @Property(name = "Number of retrieved comments")
    private Integer numRetrievedComments;
    @Property(name = "Post Length (#words)")
    private Integer postLengthWords;
    @Property(name = "Post Length (#words, no stopwords)")
    private Integer postLengthNoStop;
    @Property(name = "Average word length (characters)")
    private Double avgWordLength;
    @Property(name = "Average word length (characters, no stopwords)")
    private Double avgWordLengthNoStop;
    @Property(name = "MEIBI score")
    private Double meibiScore;
    @Property(name = "MEIBIX score")
    private Double meibixScore;
}