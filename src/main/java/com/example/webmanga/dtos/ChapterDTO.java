package com.example.webmanga.dtos;

import com.example.webmanga.entities.embedded.Chapter;
import lombok.*;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ChapterDTO {
    private String chapterName;
    private List<String> listChapterURL;

    public ChapterDTO(Chapter chapter) {
        this.chapterName = chapter.getChapterName();
        chapter.getListChapterURL().forEach(url -> {
            if (this.listChapterURL == null) {
                this.listChapterURL = new ArrayList<>();
            }
            this.listChapterURL.add(url);
        });
    }
}
