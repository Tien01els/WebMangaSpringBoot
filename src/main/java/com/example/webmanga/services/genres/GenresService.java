package com.example.webmanga.services.genres;

import com.example.webmanga.dtos.GenresDTO;
import com.example.webmanga.response.ResponseObject;
import org.springframework.stereotype.Service;

@Service
public interface GenresService {
    ResponseObject addGenres(GenresDTO genresDTO);
}
