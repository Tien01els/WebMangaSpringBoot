package com.example.webmanga.services.comic;

import com.example.webmanga.dtos.ComicDTO;
import com.example.webmanga.dtos.ResponseObject;
import com.example.webmanga.entities.Comic;
import com.example.webmanga.repositories.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComicServiceImpl implements ComicService {

    @Autowired
    ComicRepository comicRepository;

    @Override
    public ResponseObject addComic(ComicDTO comicDTO) {
        return new ResponseObject("Success", "Add comic successfully",
                new ComicDTO(comicRepository.save(new Comic(comicDTO))));
    }
}
