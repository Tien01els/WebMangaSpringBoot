package com.example.webmanga.entities;

import com.example.webmanga.dtos.GenresDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor


@Document(collection = "Genres")
public class Genres {
    @Id
    private Long id;
    private String name;
    private String description;

    public Genres(GenresDTO gender) {
        this.id = gender.getId();
        this.name = gender.getName();
        this.description = gender.getDescription();
    }

}
