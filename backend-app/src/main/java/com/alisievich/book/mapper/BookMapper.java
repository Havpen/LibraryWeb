package com.alisievich.book.mapper;

import com.alisievich.book.model.Book;
import org.mapstruct.Mapper;

@Mapper
public abstract class BookMapper {
    public Book map (Integer id){
        return Book.builder()
                .id(id)
                .build();
    }

    public Integer map (Book book){
        return book.getId();
    }
}
