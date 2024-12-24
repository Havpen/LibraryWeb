package com.alisievich.javafxapp.book.book_instance.dto;

import com.alisievich.javafxapp.book.dto.BookResponseDto;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookInstanceResponseDto {
    private Integer id;
    private BookResponseDto book;
    private String barcode;
}
