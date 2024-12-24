package com.alisievich.javafxapp.book.dto;

import com.alisievich.javafxapp.genre.dto.GenreResponseDto;
import com.alisievich.javafxapp.publisher.dto.PublisherResponseDto;
import lombok.*;
import java.util.List;

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
    private PublisherResponseDto publisher;
    private GenreResponseDto genre;
    private List<BookAuthorResponseDto> authors;
}
