package com.alisievich.javafxapp.author.service;

import com.alisievich.javafxapp.author.dto.AuthorRequestDto;
import com.alisievich.javafxapp.author.dto.AuthorResponseDto;
import com.alisievich.javafxapp.author.mapper.AuthorMapper;
import com.alisievich.javafxapp.author.model.Author;
import com.alisievich.javafxapp.client.BackendClient;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.List;

public class AuthorService {
    private final BackendClient client;

    public  AuthorService(){
        client = BackendClient.getInstance();
    }

    public CompletableFuture<List<Author>> getAllAuthors(){
        AuthorMapper authorMapper = AuthorMapper.INSTANCE;
        CompletableFuture<AuthorResponseDto[]> authorDtoFuture = client.apiRequest("authors", AuthorResponseDto[].class);
        return authorDtoFuture.thenApply(authors-> Arrays.stream(authors).map(authorMapper::authorResponseDtoAuthor).toList());
    }

    public CompletableFuture<Author> getAuthorById(Integer authorId){
        AuthorMapper authorMapper = AuthorMapper.INSTANCE;
        CompletableFuture<AuthorResponseDto> authorDtoFuture = client.apiRequest("authors/" + authorId, AuthorResponseDto.class);
        return authorDtoFuture.thenApply(authorMapper::authorResponseDtoAuthor);
    }

    public CompletableFuture<Author> createAuthor(AuthorRequestDto authorRequestDto){
        AuthorMapper authorMapper = AuthorMapper.INSTANCE;
        CompletableFuture<AuthorResponseDto> authorCreateDtoFuture = client.create("authors", authorRequestDto, AuthorResponseDto.class);
        return authorCreateDtoFuture.thenApply(authorMapper::authorResponseDtoAuthor);
    }

    public CompletableFuture<Author> updateAuthor(Integer authorId, AuthorRequestDto authorRequestDto){
        AuthorMapper authorMapper = AuthorMapper.INSTANCE;
        CompletableFuture<AuthorResponseDto> authorUpdateDtoFuture = client.update("authors/" + authorId, authorRequestDto, AuthorResponseDto.class);
        return authorUpdateDtoFuture.thenApply(authorMapper::authorResponseDtoAuthor);
    }

    public CompletableFuture<Void> deleteAuthor(Integer authorId) {
        return client.delete("authors/" + authorId);
    }
}
