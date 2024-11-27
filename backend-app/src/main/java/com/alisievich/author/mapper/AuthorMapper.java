package com.alisievich.author.mapper;

import com.alisievich.author.dto.AuthorIdDto;
import com.alisievich.author.dto.AuthorResponseDto;
import com.alisievich.author.model.Author;
import com.alisievich.common.service.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AuthorMapper extends GenericMapper<Author, AuthorResponseDto> {
    @Mapping(target = "name", ignore = true)
    Author map(AuthorIdDto idDto);
}
