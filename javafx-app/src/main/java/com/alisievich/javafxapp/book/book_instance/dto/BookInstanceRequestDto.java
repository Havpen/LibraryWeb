package com.alisievich.javafxapp.book.book_instance.dto;

import com.alisievich.javafxapp.book.model.Book;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookInstanceRequestDto {
    private Integer id;
    private Book book;
    private String barcode;
}
