package com.alisievich.book.dto;

import com.alisievich.author.dto.AuthorResponseDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookAuthorResponseDto {
    BookIdDto book;
    AuthorResponseDto author;
}
