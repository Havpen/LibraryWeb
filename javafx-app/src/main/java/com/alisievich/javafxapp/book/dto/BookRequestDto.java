package com.alisievich.javafxapp.book.dto;

import com.alisievich.javafxapp.genre.dto.GenreIdDto;
import com.alisievich.javafxapp.publisher.dto.PublisherIdDto;
import lombok.*;

import java.util.List;

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
    private PublisherIdDto publisher;
    private GenreIdDto genre;
    private List<BookAuthorRequestDto> authors;
}
