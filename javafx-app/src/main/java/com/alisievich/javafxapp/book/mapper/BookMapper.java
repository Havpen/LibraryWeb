package com.alisievich.javafxapp.book.mapper;

import com.alisievich.javafxapp.author.mapper.AuthorMapper;
import com.alisievich.javafxapp.author.model.Author;
import com.alisievich.javafxapp.book.dto.BookAuthorResponseDto;
import com.alisievich.javafxapp.book.dto.BookResponseDto;
import com.alisievich.javafxapp.book.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { AuthorMapper.class })
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book bookResponseDtoToBook(BookResponseDto responseDto);

    default Author map(BookAuthorResponseDto responseDto) {
        return Author.builder()
                .id(responseDto.getAuthor().getId())
                .name(responseDto.getAuthor().getName())
                .build();
    }
}
