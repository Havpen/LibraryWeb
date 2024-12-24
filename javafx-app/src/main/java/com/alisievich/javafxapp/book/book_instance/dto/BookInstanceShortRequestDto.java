package com.alisievich.javafxapp.book.book_instance.dto;

import com.alisievich.javafxapp.book.dto.BookIdDto;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookInstanceShortRequestDto {
    private Integer id;
    private BookIdDto book;
    private String barcode;
}
