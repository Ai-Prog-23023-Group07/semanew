package com.example.semanews;

import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends Neo4jRepository<TopicEntity, Long>{
    //トピックエンティティを12個返す
    @Query("MATCH(t:Topic) RETURN t LIMIT 12")
    List<TopicEntity> findTopic();

    //指定したジャンルに関連するトピックを3つを上限に返す
    @Query("MATCH (t:Topic)<-[:content]-(g:Genre{name: $name}) RETURN t Limit 3")
    List<TopicEntity> findTopicByGenre(@Param("name") String name);
} 