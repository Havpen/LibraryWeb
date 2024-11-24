package com.alisievich.genre.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenreRequestDto {
    private Integer id;
    private String name;
}
