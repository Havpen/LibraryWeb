package com.alisievich.book.dto;

import com.alisievich.genre.dto.GenreResponseDto;
import com.alisievich.publisher.dto.PublisherResponseDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private Integer id;
    private String title;
    private Integer year;
    private String language;
    private PublisherResponseDto publisher;
    private GenreResponseDto genre;
    private List<BookAuthorResponseDto> authors;
}
