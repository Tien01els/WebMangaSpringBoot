package com.example.webmanga.services.comic;

import com.example.webmanga.dtos.AccountDTO;
import com.example.webmanga.dtos.ComicDTO;
import com.example.webmanga.response.ResponseObject;
import com.example.webmanga.entities.Comic;
import com.example.webmanga.repositories.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ComicServiceImpl implements ComicService {

    @Autowired
    ComicRepository comicRepository;

    @Override
    public ResponseObject addComic(ComicDTO comicDTO) {
        return new ResponseObject("Success", "Add comic successfully",
                new ComicDTO(comicRepository.save(new Comic(comicDTO))));
    }

    @Override
    public ResponseObject editComic(ComicDTO comicDTO) {
//        Comic comicEdited = comicRepository.findById(comicDTO.getId())
//                                .map(comic -> {
//                                    return comic;
//                                }).orElseGet(() -> { return null; });
//        if (comicEdited == null) {
//            return new ResponseObject("Fail", "Comic not found", "");
//        }
        return new ResponseObject("Success", "Updated successfully", new ComicDTO(comicRepository.save(new Comic(comicDTO))));
    }

    @Override
    public ResponseObject getComicInfo(String id) {
        Comic comic = comicRepository.findComicById(id)
                .map(comicFound -> {
                    return comicFound;
                }).orElseGet(() -> { return null; });
        if (comic == null) {
            return new ResponseObject("Fail", "Comic not found", "");
        }
        return new ResponseObject("Success", "Get comic successfully", new ComicDTO(comic));
    }

    @Override
    public ResponseObject searchComics(String name) {
        List<ComicDTO> comicDTOList = new ArrayList<>();
        List<Comic> comicList = comicRepository.findByName(name);
        comicList.forEach(comic -> {
            comicDTOList.add(new ComicDTO(comic));
        });
        return new ResponseObject("Success", "Found comics successfully", comicList);
    }


}
