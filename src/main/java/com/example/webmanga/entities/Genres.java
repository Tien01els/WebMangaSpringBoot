package com.example.webmanga.entities;

import com.example.webmanga.dtos.GenresDTO;
import lombok.*;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
