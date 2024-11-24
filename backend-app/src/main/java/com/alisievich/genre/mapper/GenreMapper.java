package com.alisievich.genre.mapper;

import com.alisievich.common.service.GenericMapper;
import com.alisievich.genre.dto.GenreResponseDto;
import com.alisievich.genre.model.Genre;
import org.mapstruct.Mapper;

@Mapper
public interface GenreMapper extends GenericMapper<Genre, GenreResponseDto> {
}
