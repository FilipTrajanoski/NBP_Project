package com.example.blogdbcompare.model.neo4j;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("Author")
@Data
public class AuthorNode {
    @Id
    @Property(name = "Author ID")
    private Long authorId;
    @Property(name = "Name")
    private String name;
    @Property(name = "MEIBI")
    private Integer meibi;
    @Property(name = "MEIBIX")
    private Integer meibix;
    @Property(name = "Average Number of Words in posts")
    private Double avgWords;
    @Property(name = "Average Number of Words in posts (without stopwords)")
    private Double avgWordsNoStop;
}
