package com.alisievich.book.mapper;

import com.alisievich.book.dto.BookDto;
import com.alisievich.book.model.Book;
import org.mapstruct.Mapper;

@Mapper
public abstract class BookDtoMapper {
    public BookDto map (Integer id){
        return BookDto.builder().id(id).build();
    }
    public abstract BookDto map (Book book);
}
