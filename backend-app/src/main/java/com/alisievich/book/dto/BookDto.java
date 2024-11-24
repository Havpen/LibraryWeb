package com.alisievich.book.dto;

import com.alisievich.genre.dto.GenreResponseDto;
import com.alisievich.publisher.dto.PublisherResponseDto;
import lombok.*;

import java.util.List;

@Getter
@Setter//ладно хватит уже сеттеры и геттеры подписывать, не смешно
@AllArgsConstructor
@NoArgsConstructor
@Builder//строитель
public class BookDto {
    private Integer id;//идентификатор книги
    private String title;// название книги
    private Integer year;// год издания
    private String language;// Язык книги
    private PublisherResponseDto publisher;// DTO представляющий издателя книги
    private GenreResponseDto genre;//DTO представляющий жанр книги
    private List<BookAuthorResponseDto> authors;// список DTO представляющий авторов книги, BookAuthorResponseDto связывает книгу с автором через BookIdDto и AuthorResponseDto
}
