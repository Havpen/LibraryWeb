package com.alisievich.book.book_instance.dto;

import lombok.*;
import com.alisievich.book.model.Book;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookInstanceResponseDto {
    private Integer id;
    private Book bookId;
    private String barcode;
}
