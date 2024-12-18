package com.alisievich.javafxapp.reader.service;

import com.alisievich.javafxapp.client.BackendClient;
import com.alisievich.javafxapp.reader.dto.ReaderRequestDto;
import com.alisievich.javafxapp.reader.dto.ReaderResponseDto;
import com.alisievich.javafxapp.reader.mapper.ReaderMapper;
import com.alisievich.javafxapp.reader.model.Reader;
import java.util.List;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class ReaderService {
    private BackendClient client;
    public ReaderService(){
        client = BackendClient.getInstance();
    }

    public CompletableFuture<List<Reader>> getAllReaders(){
        ReaderMapper readerMapper = ReaderMapper.INSTANCE;
        CompletableFuture<ReaderResponseDto[]> readerDtoFuture = client.apiRequest("readers", ReaderResponseDto[].class);
        return readerDtoFuture.thenApply(readers->Arrays.stream(readers).map(readerMapper::readerResponseDtoToReader).toList());
    }

    public CompletableFuture<Reader> getReaderById(Integer readerId){
        ReaderMapper readerMapper = ReaderMapper.INSTANCE;
        CompletableFuture<ReaderResponseDto> readerDtoFuture = client.apiRequest("readers/" + readerId, ReaderResponseDto.class);
        return readerDtoFuture.thenApply(readerMapper::readerResponseDtoToReader);
    }

    public CompletableFuture<Reader> createReader(ReaderRequestDto readerRequestDto){
        ReaderMapper readerMapper = ReaderMapper.INSTANCE;
        CompletableFuture<ReaderResponseDto> readerCreateDtoFuture = client.create("readers", readerRequestDto, ReaderResponseDto.class);
        return readerCreateDtoFuture.thenApply(readerMapper::readerResponseDtoToReader);
    }

    public CompletableFuture<Reader> updateReader(Integer readerId, ReaderRequestDto readerRequestDto){
        ReaderMapper readerMapper = ReaderMapper.INSTANCE;
        CompletableFuture<ReaderResponseDto> readerUpdateDtoFuture = client.update("readers/" + readerId, readerRequestDto, ReaderResponseDto.class);
        return readerUpdateDtoFuture.thenApply(readerMapper::readerResponseDtoToReader);
    }

    public CompletableFuture<Void> deleteReader(Integer readerId){
        return client.delete("readers/" + readerId);
    }
}
