package com.example.semanews;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node("Genre")
public class GenreEntity {

    @Id
    private Long id;

    private String name;

    @Relationship(type = "content", direction = Relationship.Direction.OUTGOING)
    private List<TopicEntity> topics;

    // コンストラクタ、ゲッター、セッターなどの必要なメソッドを追加する

    public GenreEntity() {
        // デフォルトコンストラクタが必要です
    }

    public GenreEntity(String name) {
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

    public List<TopicEntity> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicEntity> topics) {
        this.topics = topics;
    }
}