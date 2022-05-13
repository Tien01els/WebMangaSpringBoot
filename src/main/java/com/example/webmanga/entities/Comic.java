package com.example.webmanga.entities;

import com.example.webmanga.entities.embedded.Chapter;
import com.example.webmanga.enums.Mode;
import com.example.webmanga.dtos.ComicDTO;
import com.example.webmanga.entities.embedded.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "Comic")
public class Comic {
    @Id
    private Long id;
    private String comicName;
    private List<Long> author;
    private List<String> genres;
    private String status;
    private Long view;
    private String content;
    private Date lastUpdate;
    private Mode shareMode;
    private List<Chapter> listChap;

    public Comic(ComicDTO comic) {
        this.id = comic.getId();
        this.comicName = comic.getComicName();
        comic.getAuthor().forEach(author -> {
            if (this.author == null) {
                this.author = new ArrayList<>();
            }
            this.author.add(author);
        });
        comic.getGenres().forEach(genre -> {
            if (this.genres == null) {
                this.genres = new ArrayList<>();
            }
            this.genres.add(genre);
        });
        this.status = comic.getStatus();
        this.view = comic.getView();
        this.content = comic.getContent();
        this.lastUpdate = comic.getLastUpdate();
        this.shareMode = comic.getShareMode();
        comic.getListChap().forEach(chapter -> {
            if (this.listChap == null) {
                this.listChap = new ArrayList<>();
            }
            this.listChap.add(new Chapter(chapter));
        });
    }
}
