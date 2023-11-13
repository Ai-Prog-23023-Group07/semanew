package com.example.semanews;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Topic")
public class TopicEntity {
    @Id
    private final String name;
    public TopicEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
