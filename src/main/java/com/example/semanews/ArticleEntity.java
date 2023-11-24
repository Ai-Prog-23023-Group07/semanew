package com.example.semanews;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.net.URL;

@Node("Article")
public class ArticleEntity {

    @Id
    private Long id;

    private String title;
    private URL url;

    // コンストラクタ、ゲッター、セッターなどの必要なメソッドを追加する

    public ArticleEntity() {
        // デフォルトコンストラクタが必要です
    }

    public ArticleEntity(String title, URL url) {
        this.title = title;
        this.url = url;
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

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
