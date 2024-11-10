package com.alisievich.book.service;

import com.alisievich.book.dto.BookDto;
import com.alisievich.book.mapper.BookDtoMapper;
import com.alisievich.book.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final BookDtoMapper bookDtoMapper;

    public List<BookDto> getAll(){
        return IterableUtils.toList(bookRepository.findAll()).stream().map(bookDtoMapper::map)
                .collect(Collectors.toList());
    }
}
