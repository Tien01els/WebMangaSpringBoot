package com.example.webmanga.services.comic;

import com.example.webmanga.dtos.AccountDTO;
import com.example.webmanga.dtos.ComicDTO;
import com.example.webmanga.global.GlobalVariable;
import com.example.webmanga.response.ResponseObject;
import com.example.webmanga.entities.Comic;
import com.example.webmanga.repositories.ComicRepository;
import com.example.webmanga.services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
