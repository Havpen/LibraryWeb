package com.alisievich.javafxapp.genre.dto;

import lombok.*;
import org.mapstruct.Mapper;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenreIdDto {
    private Integer id;
}
