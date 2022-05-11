package com.example.webmanga.controllers;

import com.example.webmanga.dtos.ComicDTO;
import com.example.webmanga.response.ResponseObject;
import com.example.webmanga.services.comic.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/comic")
public class ComicController {
    @Autowired
    private ComicService comicService;

    @PostMapping("/addComic")
    public ResponseEntity<ResponseObject> addComic(@RequestBody ComicDTO comic) {
        return ResponseEntity.ok(comicService.addComic(comic));
    }
}
