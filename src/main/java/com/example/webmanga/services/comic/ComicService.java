package com.example.webmanga.services.comic;

import com.example.webmanga.dtos.ComicDTO;
import com.example.webmanga.response.ResponseObject;
import org.springframework.stereotype.Service;

@Service
public interface ComicService {
    ResponseObject addComic(ComicDTO comicDTO);
}
