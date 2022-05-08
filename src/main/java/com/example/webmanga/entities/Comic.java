package com.example.webmanga.entities;

import com.example.webmanga.Mode;
import com.example.webmanga.dtos.ComicDTO;
import com.example.webmanga.entities.embedded.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private List<User> author;
    private List<Genres> genres;
    private String status;
    private Long view;
    private String content;
    private Date lastUpdate;
    private Mode shareMode;

    public Comic(ComicDTO comic) {
        this.id = comic.getId();
        this.comicName = comic.getComicName();
        comic.getAuthor().forEach(author -> {
            this.author.add(new User(author));
        });
        comic.getGenres().forEach(genre -> {
            this.genres.add(new Genres(genre));
        });
        this.status = comic.getStatus();
        this.view = comic.getView();
        this.content = comic.getContent();
        this.lastUpdate = comic.getLastUpdate();
        this.shareMode = comic.getShareMode();
    }
}
