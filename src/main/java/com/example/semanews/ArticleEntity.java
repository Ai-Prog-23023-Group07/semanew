package com.example.semanews;

import java.net.URL;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Article")
public class ArticleEntity {
    @Id
    private final Long id;
    private final String name;
    private final URL url;

    public ArticleEntity(Long id, String name, URL url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return this.name;
    }

    public URL getUrl() {
        return this.url;
    }

    public String toString(){
        String str = getName() + ": " + getUrl();
        return str; 
    }
}
