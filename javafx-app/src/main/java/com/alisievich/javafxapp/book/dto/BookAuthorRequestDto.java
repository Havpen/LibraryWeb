package com.alisievich.javafxapp.book.dto;

import com.alisievich.javafxapp.author.dto.AuthorRequestDto;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookAuthorRequestDto {
    BookIdDto book;
    AuthorRequestDto author;
}
