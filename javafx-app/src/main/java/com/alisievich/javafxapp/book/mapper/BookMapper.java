package com.alisievich.javafxapp.book.mapper;

import com.alisievich.javafxapp.book.dto.BookResponseDto;
import com.alisievich.javafxapp.book.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book bookResponseDtoToBook(BookResponseDto responseDto);
}
