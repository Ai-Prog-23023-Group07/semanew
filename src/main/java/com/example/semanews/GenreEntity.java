package com.example.semanews;

import java.util.Set;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node("Genre")
public class GenreEntity {
    @Id
    private final Long id;
    private final String name;

    public GenreEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Relationship(type = "content")
    private Set<TopicEntity> topics;

    public String toString(){
        return getName();
    }
}