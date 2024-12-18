package com.alisievich.javafxapp.author.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorRequestDto {
    private Integer id;
    private String name;
}
