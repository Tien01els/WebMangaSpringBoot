package com.example.webmanga.dtos;

import com.example.webmanga.entities.Genres;
import lombok.*;
import org.springframework.data.annotation.Transient;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GenresDTO {
    @Transient
    public static final String SEQUENCE_NAME = "genres_sequence";
    private Long id;
    private String name;
    private String description;

    public GenresDTO(Genres gender) {
        this.id = gender.getId();
        this.name = gender.getName();
        this.description = gender.getDescription();
    }
}
