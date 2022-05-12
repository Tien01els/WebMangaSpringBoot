package com.example.webmanga.services.genres;

import com.example.webmanga.dtos.ComicDTO;
import com.example.webmanga.dtos.GenresDTO;
import com.example.webmanga.response.ResponseObject;
import com.example.webmanga.entities.Genres;
import com.example.webmanga.repositories.GenresRepository;
import com.example.webmanga.services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenresServiceImpl implements GenresService {

    @Autowired
    SequenceGeneratorService sequenceGenerator;
    @Autowired
    GenresRepository genresRepository;

    @Override
    public ResponseObject addGenres(GenresDTO genresDTO) {
        genresDTO.setId(sequenceGenerator.generateSequence(GenresDTO.SEQUENCE_NAME));
        return new ResponseObject("Success", "Add genres successfully",
                new GenresDTO(genresRepository.save(new Genres(genresDTO))));
    }
}
