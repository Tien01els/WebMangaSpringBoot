package com.example.webmanga.controllers;

import com.example.webmanga.dtos.ComicDTO;
import com.example.webmanga.response.ResponseObject;
import com.example.webmanga.services.comic.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/comic")
public class ComicController {
    @Autowired
    private ComicService comicService;

    @PostMapping("/addComic")
    public ResponseEntity<ResponseObject> addComic(@RequestBody ComicDTO comic) {
        return ResponseEntity.ok(comicService.addComic(comic));
    }

    @PutMapping("/editComic")
    public ResponseEntity<ResponseObject> editComic(@RequestBody ComicDTO comic) {
        return ResponseEntity.ok(comicService.editComic(comic));
    }

    @GetMapping("/getComicInfo/{id}")
    public ResponseEntity<ResponseObject> getComicInfo(@PathVariable String id) {
        return ResponseEntity.ok(comicService.getComicInfo(id));
    }

    @GetMapping("/searchComics")
    public ResponseEntity<ResponseObject> searchComics(@RequestParam String search) {
        return ResponseEntity.ok(comicService.searchComics(search));
    }


}
