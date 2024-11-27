package com.alisievich.book.book_instance.dto;

import com.alisievich.book.dto.BookResponseDto;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookInstanceResponseDto {
    private Integer id;
    private BookResponseDto book;
    private String barcode;
}
