package com.alisievich.javafxapp.book.book_instance.mapper;

import com.alisievich.javafxapp.book.book_instance.dto.BookInstanceResponseDto;
import com.alisievich.javafxapp.book.book_instance.model.BookInstance;
import com.alisievich.javafxapp.book.mapper.BookMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = BookMapper.class)
public interface BookInstanceMapper {
    BookInstanceMapper INSTANCE = Mappers.getMapper(BookInstanceMapper.class);
    BookInstance bookInstanceResponseDtoBookInstance(BookInstanceResponseDto responseDto);
}
