package com.alisievich.book.dto;

import com.alisievich.author.dto.AuthorIdDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookAuthorRequestDto {
    BookIdDto book;
    AuthorIdDto author;
}
