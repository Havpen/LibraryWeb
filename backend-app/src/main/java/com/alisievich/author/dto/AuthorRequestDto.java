package com.alisievich.author.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorRequestDto {
    private Integer id;
    private String name;
}
