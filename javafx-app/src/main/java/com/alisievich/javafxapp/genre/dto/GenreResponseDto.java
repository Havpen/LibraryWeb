package com.alisievich.javafxapp.genre.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreResponseDto {
    private Integer id;
    private String name;
}
