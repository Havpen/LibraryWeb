package com.alisievich.book.book_instance.dto;
import com.alisievich.book.dto.BookIdDto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookInstanceRequestDto {
    private Integer id;
    private BookIdDto book;
    private String barcode;
}
