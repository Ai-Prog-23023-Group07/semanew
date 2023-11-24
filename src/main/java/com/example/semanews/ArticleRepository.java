package com.example.semanews;

import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends Neo4jRepository<ArticleEntity, Long>{
    //アーティクルエンティティを12個返す
    @Query("MATCH(a:Article) RETURN a LIMIT 12")
    List<ArticleEntity> findArticle();

    //指定したトピックを関連する記事を2つを上限に返す
    @Query("MATCH(a:Article)<-[:has]-(t:Topic{name: $name}) RETURN a LIMIT 2")
    List<ArticleEntity> findArticleByTopic(@Param("name") String name);
}
