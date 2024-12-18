package com.alisievich.javafxapp.book.book_instance.dto;

import com.alisievich.javafxapp.book.model.Book;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookInstanceResponseDto {
    private Integer id;
    private Book book;
    private String barcode;
}
