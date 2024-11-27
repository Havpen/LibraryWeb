package com.alisievich.book.book_instance.service;

import com.alisievich.book.model.Book;
import com.alisievich.book.repository.BookRepository;
import com.alisievich.book.book_instance.dto.BookInstanceRequestDto;
import com.alisievich.book.book_instance.model.BookInstance;
import com.alisievich.book.book_instance.repository.BookInstanceRepository;
import com.alisievich.common.service.CrudService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BookInstanceService extends CrudService<BookInstance, Integer> {
    private final BookInstanceRepository instanceRepository;
    private final BookRepository bookRepository;

    public BookInstanceService(BookInstanceRepository bookInstanceRepository, BookRepository bookRepository){
        super(bookInstanceRepository);
        this.instanceRepository = bookInstanceRepository;
        this.bookRepository = bookRepository;

    }

    public BookInstance create(BookInstanceRequestDto requestDto){
        Book book = bookRepository.findById(requestDto.getBook().getId()).orElseThrow(EntityNotFoundException::new);
        BookInstance instance = BookInstance.builder()
                .book(book)
                .barcode(requestDto.getBarcode())
                .build();
        return instanceRepository.save(instance);
    }

    public BookInstance update(Integer id, BookInstanceRequestDto requestDto){
        BookInstance instance = instanceRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Book book = bookRepository.findById(requestDto.getBook().getId()).orElseThrow(EntityNotFoundException::new);
        instance.setBook(book);
        instance.setBarcode(requestDto.getBarcode());
        return instanceRepository.save(instance);
    }
}
