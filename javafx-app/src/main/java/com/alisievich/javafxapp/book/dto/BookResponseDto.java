package com.alisievich.javafxapp.book.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponseDto {
    private Integer id;
    private String title;
    private Integer year;
    private String language;
    //private PublisherResponseDto publisher;
    //private GenreResponseDto genre;
    //private List<BookAuthorResponseDto> authors;
}
