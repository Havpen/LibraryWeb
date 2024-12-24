package com.alisievich.javafxapp.book.book_instance.service;

import com.alisievich.javafxapp.book.book_instance.dto.BookInstanceRequestDto;
import com.alisievich.javafxapp.book.book_instance.dto.BookInstanceResponseDto;
import com.alisievich.javafxapp.book.book_instance.mapper.BookInstanceMapper;
import com.alisievich.javafxapp.book.book_instance.model.BookInstance;
import com.alisievich.javafxapp.client.BackendClient;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BookInstanceService {
    private BackendClient client;
    public BookInstanceService(){
        client = BackendClient.getInstance();
    }
    public CompletableFuture<List<BookInstance>> getAllBookInstances(){
        BookInstanceMapper bookInstanceMapper = BookInstanceMapper.INSTANCE;
        CompletableFuture<BookInstanceResponseDto[]> bookInstanceDtoFuture = client.apiRequest("bookInstances", BookInstanceResponseDto[].class);
        return bookInstanceDtoFuture.thenApply(bookInstances-> Arrays.stream(bookInstances).map(bookInstanceMapper::bookInstanceResponseDtoBookInstance).toList());
    }
    public CompletableFuture<BookInstance> getBookInstanceById(Integer bookInstanceId){
        BookInstanceMapper bookInstanceMapper = BookInstanceMapper.INSTANCE;
        CompletableFuture<BookInstanceResponseDto> bookInstanceDtoFuture = client.apiRequest("bookInstances/" + bookInstanceId, BookInstanceResponseDto.class);
        return bookInstanceDtoFuture.thenApply(bookInstanceMapper::bookInstanceResponseDtoBookInstance);
    }
    public CompletableFuture<BookInstance> createBookInstance(BookInstanceRequestDto bookInstanceRequestDto){
        BookInstanceMapper bookInstanceMapper = BookInstanceMapper.INSTANCE;
        CompletableFuture<BookInstanceResponseDto> createBookInstanceDtoFuture = client.create("bookInstances", bookInstanceRequestDto, BookInstanceResponseDto.class);
        return createBookInstanceDtoFuture.thenApply(bookInstanceMapper::bookInstanceResponseDtoBookInstance);
    }
    public CompletableFuture<BookInstance> updateBookInstance(Integer bookInstanceId, BookInstanceRequestDto bookInstanceRequestDto){
        BookInstanceMapper bookInstanceMapper = BookInstanceMapper.INSTANCE;
        CompletableFuture<BookInstanceResponseDto> updateBookInstanceDtoFuture = client.update("bookInstances/" + bookInstanceId, bookInstanceRequestDto, BookInstanceResponseDto.class);
        return updateBookInstanceDtoFuture.thenApply(bookInstanceMapper::bookInstanceResponseDtoBookInstance);
    }
    public CompletableFuture<Void> deleteBookInstance(Integer bookInstanceId){
        return client.delete("bookInstances/" + bookInstanceId);
    }
}
