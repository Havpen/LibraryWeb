package com.alisievich.author.mapper;

import com.alisievich.author.dto.AuthorResponseDto;
import com.alisievich.author.model.Author;
import com.alisievich.common.service.GenericMapper;
import org.mapstruct.Mapper;

@Mapper
public interface AuthorMapper extends GenericMapper<Author, AuthorResponseDto> {
}
