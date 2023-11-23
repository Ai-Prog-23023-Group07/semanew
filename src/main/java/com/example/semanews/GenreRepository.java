package com.example.semanews;

import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface GenreRepository extends Neo4jRepository<GenreEntity, Long> {

    @Query("MATCH (g:Genre) RETURN g Limit 4")
    List<GenreEntity> findGenre();
}
