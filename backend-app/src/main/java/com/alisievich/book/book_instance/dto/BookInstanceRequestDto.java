package com.alisievich.book.book_instance.dto;
import com.alisievich.book.model.Book;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookInstanceRequestDto {
    private Integer id;
    private Book bookId;
    private String barcode;
}
