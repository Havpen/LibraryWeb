package com.alisievich.javafxapp.genre.service;

import com.alisievich.javafxapp.genre.dto.GenreResponseDto;
import com.alisievich.javafxapp.genre.dto.GenreRequestDto;
import com.alisievich.javafxapp.genre.mapper.GenreMapper;
import com.alisievich.javafxapp.genre.model.Genre;
import com.alisievich.javafxapp.client.BackendClient;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GenreService {
    private final BackendClient client;
    public GenreService() {
        client = BackendClient.getInstance();
    }
    public CompletableFuture<List<Genre>> getAllGenres() {
        GenreMapper genreMapper = GenreMapper.INSTANCE;
        CompletableFuture<GenreResponseDto[]> genresDtoFuture = client.apiRequest("genres", GenreResponseDto[].class);
        return genresDtoFuture.thenApply(genres -> Arrays.stream(genres)
                .map(genreMapper::genreResponseDtoToGenre)
                .toList());
    }
    public CompletableFuture<Genre> getGenreById(Integer genreId) {
        GenreMapper genreMapper = GenreMapper.INSTANCE;
        CompletableFuture<GenreResponseDto> genreDtoFuture = client.apiRequest("genres/" + genreId, GenreResponseDto.class);
        return genreDtoFuture.thenApply(genreMapper::genreResponseDtoToGenre);
    }
    public CompletableFuture<Genre> createGenre(GenreRequestDto genreRequestDto) {
        GenreMapper genreMapper = GenreMapper.INSTANCE;
        CompletableFuture<GenreResponseDto> createdGenreDtoFuture = client.create("genres", genreRequestDto, GenreResponseDto.class);
        return createdGenreDtoFuture.thenApply(genreMapper::genreResponseDtoToGenre);
    }
    public CompletableFuture<Genre> updateGenre(Integer genreId, GenreRequestDto genreRequestDto) {
        GenreMapper genreMapper = GenreMapper.INSTANCE;
        CompletableFuture<GenreResponseDto> updatedGenreDtoFuture = client.update("genres/" + genreId, genreRequestDto, GenreResponseDto.class);
        return updatedGenreDtoFuture.thenApply(genreMapper::genreResponseDtoToGenre);
    }
    public CompletableFuture<Void> deleteGenre(Integer genreId) {
        return client.delete("genres/" + genreId);
    }
}

