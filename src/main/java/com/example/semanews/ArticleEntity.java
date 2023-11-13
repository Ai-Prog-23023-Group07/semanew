package com.example.semanews;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Article")
public class ArticleEntity {
    @Id
    private final String name;
    private final String url;

    public ArticleEntity(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return this.name;
    }

    public String GetUrl() {
        return this.url;
    }
}
