package com.example.webmanga.dtos;

import com.example.webmanga.Mode;
import com.example.webmanga.entities.Comic;
import lombok.*;
import org.springframework.data.annotation.Transient;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ComicDTO {
    @Transient
    public static final String SEQUENCE_NAME = "comics_sequence";
    private Long id;
    private String comicName;
    private List<UserDTO> author;
    private List<GenresDTO> genres;
    private String status;
    private Long view;
    private String content;
    private Date lastUpdate;
    private Mode shareMode;

    public ComicDTO(Comic comic) {
        this.id = comic.getId();
        this.comicName = comic.getComicName();
        comic.getAuthor().forEach(author -> {
            this.author.add(new UserDTO(author));
        });
        comic.getGenres().forEach(genre -> {
            this.genres.add(new GenresDTO(genre));
        });
        this.status = comic.getStatus();
        this.view = comic.getView();
        this.content = comic.getContent();
        this.lastUpdate = comic.getLastUpdate();
        this.shareMode = comic.getShareMode();
    }
}
