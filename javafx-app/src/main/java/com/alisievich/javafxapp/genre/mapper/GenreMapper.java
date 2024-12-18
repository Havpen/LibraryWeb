package com.alisievich.javafxapp.genre.mapper;

import com.alisievich.javafxapp.genre.dto.GenreResponseDto;
import com.alisievich.javafxapp.genre.model.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GenreMapper {
    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);
    Genre genreResponseDtoToGenre(GenreResponseDto responseDto);
}
