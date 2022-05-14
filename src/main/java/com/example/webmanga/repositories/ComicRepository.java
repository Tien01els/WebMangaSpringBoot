package com.example.webmanga.repositories;

import com.example.webmanga.entities.Comic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComicRepository extends MongoRepository<Comic, Long> {
    @Query("{'comicName': {$regex: ?0}}")
    List<Comic> findByName(String comicName);
}
