package com.alisievich.book.mapper;

import com.alisievich.author.mapper.AuthorMapper;
import com.alisievich.book.dto.BookIdDto;
import com.alisievich.book.dto.BookRequestDto;
import com.alisievich.book.dto.BookResponseDto;
import com.alisievich.book.model.Book;
import com.alisievich.genre.mapper.GenreMapper;
import com.alisievich.publisher.mapper.PublisherMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(uses = { PublisherMapper.class, GenreMapper.class, AuthorMapper.class, BookAuthorMapper.class })
public interface BookMapper {
    BookResponseDto map(Book book);
    Book map(BookRequestDto book);

    @Mapping(target = "id", source = "idDto.id")
    @Mapping(target = "title", ignore = true)
    @Mapping(target = "year", ignore = true)
    @Mapping(target = "language", ignore = true)
    @Mapping(target = "publisher", ignore = true)
    @Mapping(target = "genre", ignore = true)
    @Mapping(target = "authors", ignore = true)
    Book map(BookIdDto idDto);

    void updateTargetFromSource(BookRequestDto requestDto, @MappingTarget Book book);
}
