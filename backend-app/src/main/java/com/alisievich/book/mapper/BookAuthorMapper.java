package com.alisievich.book.mapper;

import com.alisievich.author.mapper.AuthorMapper;
import com.alisievich.book.dto.BookAuthorRequestDto;
import com.alisievich.book.model.BookAuthor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = { BookMapper.class, AuthorMapper.class })
public interface BookAuthorMapper {
    @Mapping(target = "id", expression = "java(new BookAuthor.BookAuthorId(requestDto.getAuthor().getId(), requestDto.getBook().getId()))")
    BookAuthor map(BookAuthorRequestDto requestDto);
}
