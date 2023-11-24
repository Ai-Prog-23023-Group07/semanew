package com.example.semanews;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node("Topic")
public class TopicEntity {

    @Id
    private Long id;

    private String name;


    @Relationship(type = "has", direction = Relationship.Direction.OUTGOING)
    private List<ArticleEntity> articles;

    // コンストラクタ、ゲッター、セッターなどの必要なメソッドを追加する

    public TopicEntity() {
        // デフォルトコンストラクタが必要です
    }

    public TopicEntity(String name) {
        this.name = name;
    }

    // ゲッターとセッターを追加

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ArticleEntity> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleEntity> articles) {
        this.articles = articles;
    }

    public String toString() {
        return getName();
    }
}

