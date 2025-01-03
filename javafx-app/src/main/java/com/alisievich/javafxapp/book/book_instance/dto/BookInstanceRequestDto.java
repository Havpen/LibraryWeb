package com.alisievich.javafxapp.book.book_instance.dto;

import com.alisievich.javafxapp.book.dto.BookRequestDto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookInstanceRequestDto {
    private Integer id;
    private BookRequestDto book;
    private String barcode;
}
