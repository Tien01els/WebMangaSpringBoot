package com.example.webmanga.controllers;

import com.example.webmanga.dtos.ComicDTO;
import com.example.webmanga.dtos.GenresDTO;
import com.example.webmanga.response.ResponseObject;
import com.example.webmanga.services.comic.ComicService;
import com.example.webmanga.services.genres.GenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/genres")
public class GenresController {

    @Autowired
    private GenresService genresService;

    @PostMapping("/addGenres")
    public ResponseEntity<ResponseObject> addComic(@RequestBody GenresDTO genresDTO) {
        return ResponseEntity.ok(genresService.addGenres(genresDTO));
    }
}
