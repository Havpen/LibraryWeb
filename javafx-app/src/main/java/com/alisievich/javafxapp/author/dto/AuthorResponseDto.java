package com.alisievich.javafxapp.author.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponseDto {
    private Integer id;
    private String name;
}
