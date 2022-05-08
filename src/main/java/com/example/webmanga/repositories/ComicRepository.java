package com.example.webmanga.repositories;

import com.example.webmanga.entities.Comic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicRepository extends MongoRepository<Comic, Long> {
}
