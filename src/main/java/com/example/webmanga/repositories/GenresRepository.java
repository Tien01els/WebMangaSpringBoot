package com.example.webmanga.repositories;

import com.example.webmanga.entities.Genres;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenresRepository extends MongoRepository<Genres, Long> {
}
