package com.alisievich.book.service;

import com.alisievich.book.dto.BookDto;
import com.alisievich.book.dto.BookRequestDto;
import com.alisievich.book.mapper.BookDtoMapper;
import com.alisievich.book.mapper.BookMapper;
import com.alisievich.book.model.Book;
import com.alisievich.book.repository.BookRepository;
import com.alisievich.genre.model.Genre;
import com.alisievich.genre.repository.GenreRepository;
import com.alisievich.publisher.model.Publisher;
import com.alisievich.publisher.repository.PublisherRepository;
import jakarta.persistence.EntityNotFoundException;
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
    private final BookMapper bookMapper;
    private final GenreRepository genreRepository;
    private final PublisherRepository publisherRepository;

    public List<BookDto> getAll(){
        return IterableUtils.toList(bookRepository.findAll()).stream().map(bookDtoMapper::map)
                .collect(Collectors.toList());
    }

    public Book getById(Integer id) {
        return bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Book create(BookRequestDto requestDto){
        Book book = bookMapper.map(requestDto);
        return bookRepository.save(book);
    }

    public Book update(Integer id, BookRequestDto requestDto) {
        Book book = bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        Genre genre = genreRepository.findById(requestDto.getGenre().getId()).orElseThrow(EntityNotFoundException::new);
        Publisher publisher = publisherRepository.findById(requestDto.getPublisher().getId()).orElseThrow(EntityNotFoundException::new);

        book.setGenre(genre);
        book.setPublisher(publisher);
        book.setTitle(requestDto.getTitle());
        book.setYear(requestDto.getYear());
        book.setLanguage(requestDto.getLanguage());

        // TODO: Add authors update

        return bookRepository.save(book);
    }

    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }
}
