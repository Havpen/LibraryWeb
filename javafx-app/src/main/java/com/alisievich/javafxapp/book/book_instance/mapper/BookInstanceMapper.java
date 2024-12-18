package com.alisievich.javafxapp.book.book_instance.mapper;

import com.alisievich.javafxapp.book.book_instance.dto.BookInstanceResponseDto;
import com.alisievich.javafxapp.book.book_instance.model.BookInstance;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookInstanceMapper {
    BookInstanceMapper INSTANCE = Mappers.getMapper(BookInstanceMapper.class);
    BookInstance bookInstanceResponseDtoBookInstance(BookInstanceResponseDto responseDto);
}
