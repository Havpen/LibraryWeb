package com.alisievich.genre.mapper;

import com.alisievich.common.service.GenericMapper;
import com.alisievich.genre.dto.GenreIdDto;
import com.alisievich.genre.dto.GenreResponseDto;
import com.alisievich.genre.model.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GenreMapper extends GenericMapper<Genre, GenreResponseDto> {
    @Mapping(target = "name", ignore = true)
    Genre map(GenreIdDto idDto);
}
