package com.example.blogdbcompare.model.neo4j;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.ZonedDateTime;

@Node("Inlink")
@Data
public class InlinkNode {
    @Id
    @Property(name = "Inlink ID")
    private Long inlinkId;
    @Relationship(type = "links_to", direction = Relationship.Direction.OUTGOING)
    @Property(name = "Post ID")
    private PostNode post;
    @Property(name = "Title")
    private String title;
    @Property(name = "Author")
    private String author;
    @Property(name = "Date")
    private ZonedDateTime date;
    @Property(name = "URL")
    private String url;
}
