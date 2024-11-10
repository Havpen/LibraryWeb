package com.alisievich.book.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private Integer id;
    private String code;
    private String name;
    private String locale;
}
