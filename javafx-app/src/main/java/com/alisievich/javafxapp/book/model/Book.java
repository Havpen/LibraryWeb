package com.alisievich.javafxapp.book.model;

import com.alisievich.javafxapp.author.model.Author;
import com.alisievich.javafxapp.genre.model.Genre;
import com.alisievich.javafxapp.publisher.model.Publisher;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Integer id;
    private String title;
    private Integer year;
    private String language;
    private Publisher publisher;
    private Genre genre;
    @Builder.Default
    private List<Author> authors = new ArrayList<>();
    @Override
    public String toString() {
        return getTitle();
    }
}
