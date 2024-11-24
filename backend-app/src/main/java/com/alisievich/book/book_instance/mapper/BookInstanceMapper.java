package com.alisievich.book.book_instance.mapper;

import com.alisievich.common.service.GenericMapper;
import com.alisievich.book.book_instance.dto.BookInstanceResponseDto;
import com.alisievich.book.book_instance.model.BookInstance;
import org.mapstruct.Mapper;

@Mapper
public interface BookInstanceMapper extends GenericMapper<BookInstance, BookInstanceResponseDto> {
}
