package com.alisievich.javafxapp.author.mapper;

import com.alisievich.javafxapp.author.dto.AuthorResponseDto;
import com.alisievich.javafxapp.author.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);
    Author authorResponseDtoAuthor(AuthorResponseDto responseDto);
}
