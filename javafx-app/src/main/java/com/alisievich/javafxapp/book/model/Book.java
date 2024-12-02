package com.alisievich.javafxapp.book.model;

import lombok.*;

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
    //private Publisher publisher;
    //private Genre genre;
    //private List<BookAuthor> authors = new ArrayList<>();
}
