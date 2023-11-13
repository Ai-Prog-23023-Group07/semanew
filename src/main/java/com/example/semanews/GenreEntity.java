package com.example.semanews;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Genre")
public class GenreEntity {
    @Id
    private final String name;
    public GenreEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
