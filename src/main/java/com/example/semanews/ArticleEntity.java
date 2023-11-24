package com.example.semanews;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.net.URL;

@Node("Article")
public class ArticleEntity {

    @Id
    private Long id;

    private String title;
    private String  URL;

    // コンストラクタ、ゲッター、セッターなどの必要なメソッドを追加する

    public ArticleEntity() {
        // デフォルトコンストラクタが必要です
    }

    public ArticleEntity(String title, String  URL) {
        this.title = title;
        this. URL =  URL;
    }

    // ゲッターとセッターを追加

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return  URL;
    }

    public void setUrl(String  URL) {
        this. URL =  URL;
    }

    public String toString() {
        return getTitle() + ": " + getUrl();
    }
}
