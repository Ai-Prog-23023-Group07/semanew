package com.example.semanews;

import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends Neo4jRepository<GenreEntity, Long> {

    @Query("MATCH (g:Genre) RETURN g ORDER BY rand() Limit 4")
    List<GenreEntity> findGenre();

    @Query("MATCH (t:Topic{name: $name})<-[:content]-(g:Genre) RETURN g")
    GenreEntity findGenreByTopic(@Param("name") String name);
}
