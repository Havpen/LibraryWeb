package com.alisievich.javafxapp.book.service;

import com.alisievich.javafxapp.book.dto.BookResponseDto;
import com.alisievich.javafxapp.book.dto.BookRequestDto;
import com.alisievich.javafxapp.book.mapper.BookMapper;
import com.alisievich.javafxapp.book.model.Book;
import com.alisievich.javafxapp.client.BackendClient;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BookService {
    private final BackendClient client;
    public BookService() {
        client = BackendClient.getInstance();
    }
    public CompletableFuture<List<Book>> getAllBooks() {
        BookMapper bookMapper = BookMapper.INSTANCE;
        CompletableFuture<BookResponseDto[]> booksDtoFuture = client.apiRequest("books", BookResponseDto[].class);
        return booksDtoFuture.thenApply(books -> Arrays.stream(books).map(bookMapper::bookResponseDtoToBook).toList());
    }
    public CompletableFuture<Book> getBookById(Integer bookId) {
        BookMapper bookMapper = BookMapper.INSTANCE;
        CompletableFuture<BookResponseDto> bookDtoFuture = client.apiRequest("books/" + bookId, BookResponseDto.class);
        return bookDtoFuture.thenApply(bookMapper::bookResponseDtoToBook);
    }
    public CompletableFuture<Book> createBook(BookRequestDto bookRequestDto) {
        BookMapper bookMapper = BookMapper.INSTANCE;
        CompletableFuture<BookResponseDto> createdBookDtoFuture = client.create("books", bookRequestDto, BookResponseDto.class);
        return createdBookDtoFuture.thenApply(bookMapper::bookResponseDtoToBook);
    }
    public CompletableFuture<Book> updateBook(Integer bookId, BookRequestDto bookRequestDto) {
        BookMapper bookMapper = BookMapper.INSTANCE;
        CompletableFuture<BookResponseDto> updatedBookDtoFuture = client.update("books/" + bookId, bookRequestDto, BookResponseDto.class);
        return updatedBookDtoFuture.thenApply(bookMapper::bookResponseDtoToBook);
    }
    public CompletableFuture<Void> deleteBook(Integer bookId) {
        return client.delete("books/" + bookId);
    }
}



