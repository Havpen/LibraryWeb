package com.alisievich.book.book_instance.service;

import com.alisievich.book.model.Book;
import com.alisievich.book.repository.BookRepository;
import com.alisievich.book.book_instance.dto.BookInstanceRequestDto;
import com.alisievich.book.book_instance.model.BookInstance;
import com.alisievich.book.book_instance.repository.BookInstanceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookInstanceService {
    private final BookInstanceRepository instanceRepository;
    private final BookRepository bookRepository;

    public BookInstance create(BookInstanceRequestDto requestDto){
        Book book = bookRepository.findById(requestDto.getBook().getId()).orElseThrow(EntityNotFoundException::new);
        BookInstance instance = BookInstance.builder().bookId(book).build();
        return instanceRepository.save(instance);
    }

    public BookInstance update(Integer id, BookInstanceRequestDto requestDto){
        BookInstance instance = instanceRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Book book = bookRepository.findById(requestDto.getBook().getId()).orElseThrow(EntityNotFoundException::new);
        instance.setBookId(book);
        return instanceRepository.save(instance);
    }
}
