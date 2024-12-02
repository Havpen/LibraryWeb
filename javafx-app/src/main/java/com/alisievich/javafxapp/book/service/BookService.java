package com.alisievich.javafxapp.book.service;

import com.alisievich.javafxapp.book.dto.BookResponseDto;
import com.alisievich.javafxapp.book.mapper.BookMapper;
import com.alisievich.javafxapp.book.model.Book;
import com.alisievich.javafxapp.client.BackendClient;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BookService {
    private BackendClient client;

    public BookService() {
        client = BackendClient.getInstance();
    }

    public CompletableFuture<List<Book>> getAllBooks() {
        BookMapper bookMapper = BookMapper.INSTANCE;
        CompletableFuture<BookResponseDto[]> booksDtoFuture = client.apiRequest("books", BookResponseDto[].class);
        return booksDtoFuture.thenApply((books) -> {
            return Arrays.stream(books).map(bookMapper::bookResponseDtoToBook).toList();
        });
    }
}
