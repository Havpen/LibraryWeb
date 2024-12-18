package com.alisievich.javafxapp.book.dto;

import com.alisievich.javafxapp.author.dto.AuthorResponseDto;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookAuthorResponseDto {
    AuthorResponseDto author;
}
