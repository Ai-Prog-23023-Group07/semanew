package com.example.semanews;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Topic")
public class TopicEntity {
    @Id
    private final Long id;
    private final String name;
    public TopicEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return getName();
    }
}
