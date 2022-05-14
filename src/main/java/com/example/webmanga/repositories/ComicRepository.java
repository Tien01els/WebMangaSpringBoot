package com.example.webmanga.repositories;

import com.example.webmanga.entities.Comic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComicRepository extends MongoRepository<Comic, String> {
    @Query("{'comicName': { $regex: ?0 }}")
    List<Comic> findByName(String comicName);

    @Query("{'Id' : ?0}")
    Optional<Comic> findComicById(String id);
}
