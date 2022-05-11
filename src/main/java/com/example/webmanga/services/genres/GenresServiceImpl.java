package com.example.webmanga.services.genres;

import com.example.webmanga.dtos.GenresDTO;
import com.example.webmanga.response.ResponseObject;
import com.example.webmanga.entities.Genres;
import com.example.webmanga.repositories.GenresRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class GenresServiceImpl implements GenresService {

    @Autowired
    GenresRepository genresRepository;

    @Override
    public ResponseObject addGenres(GenresDTO genresDTO) {
        return new ResponseObject("Success", "Add genres successfully",
                new GenresDTO(genresRepository.save(new Genres(genresDTO))));
    }
}
