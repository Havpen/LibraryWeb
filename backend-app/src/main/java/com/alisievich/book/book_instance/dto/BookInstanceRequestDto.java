package com.alisievich.book.book_instance.dto;

import com.alisievich.book.dto.BookRequestDto;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookInstanceRequestDto {
    private Integer id;
    private BookRequestDto book;
    private String barcode;
}
