package com.example.webmanga.services.comic;

import com.example.webmanga.dtos.AccountDTO;
import com.example.webmanga.dtos.ComicDTO;
import com.example.webmanga.global.GlobalVariable;
import com.example.webmanga.response.ResponseObject;
import com.example.webmanga.entities.Comic;
import com.example.webmanga.repositories.ComicRepository;
import com.example.webmanga.services.SequenceGeneratorService;
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
    SequenceGeneratorService sequenceGenerator;

    @Autowired
    ComicRepository comicRepository;

    @Override
    public ResponseObject addComic(ComicDTO comicDTO) {
        comicDTO.getAuthor().add(GlobalVariable.accountID);
        comicDTO.setId(sequenceGenerator.generateSequence(ComicDTO.SEQUENCE_NAME));
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
    public ResponseObject getComic(Long id) {
        Comic comic = comicRepository.findById(id)
                .map(comicFound -> {
                    return comicFound;
                }).orElseGet(() -> { return null; });
        if (comic == null) {
            return new ResponseObject("Fail", "Comic not found", "");
        }
        return new ResponseObject("Success", "Updated successfully", new ComicDTO(comic));
    }
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public ResponseObject searchComics(String name) {
        List<ComicDTO> comicDTOList = new ArrayList<>();
        String comicName = name + "?";
        Query query = new Query();
        query.addCriteria(Criteria.where("comicName").regex(comicName));
        List<Comic> comicList = mongoTemplate.find(query, Comic.class);
        comicList.forEach(comic -> {
            comicDTOList.add(new ComicDTO(comic));
        });
        return new ResponseObject("Success", "Found comics successfully", comicList);
    }


}
