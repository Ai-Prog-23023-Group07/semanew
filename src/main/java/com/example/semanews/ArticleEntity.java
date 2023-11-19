package com.example.semanews;

import java.net.URL;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Article")
public class ArticleEntity {
    @Id
    private final String name;
    private final URL url;

    public ArticleEntity(String name, URL url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return this.name;
    }

    public URL GetUrl() {
        return this.url;
    }
}
