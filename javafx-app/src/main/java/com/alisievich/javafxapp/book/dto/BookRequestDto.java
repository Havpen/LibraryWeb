package com.alisievich.javafxapp.book.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequestDto {
    private Integer id;
    private String title;
    private Integer year;
    private String language;
    //private PublisherIdDto publisher;
    //private GenreIdDto genre;
    //private List<BookAuthorRequestDto> authors;
}
