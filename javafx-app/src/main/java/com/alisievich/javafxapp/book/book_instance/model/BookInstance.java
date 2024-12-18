package com.alisievich.javafxapp.book.book_instance.model;

import com.alisievich.javafxapp.book.model.Book;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookInstance {
    private Integer id;
    private Book book;
    private String barcode;
}
