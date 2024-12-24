package com.alisievich.javafxapp.publisher.service;

import com.alisievich.javafxapp.client.BackendClient;
import com.alisievich.javafxapp.publisher.dto.PublisherRequestDto;
import com.alisievich.javafxapp.publisher.dto.PublisherResponseDto;
import com.alisievich.javafxapp.publisher.mapper.PublisherMapper;
import com.alisievich.javafxapp.publisher.model.Publisher;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PublisherService {
    private final BackendClient client;
    public  PublisherService(){
        client = BackendClient.getInstance();
    }
    public CompletableFuture<List<Publisher>> getAllPublishers() {
        PublisherMapper publisherMapper = PublisherMapper.INSTANCE;
        CompletableFuture<PublisherResponseDto[]> publisherDtoFuture = client.apiRequest("publishers", PublisherResponseDto[].class);
        return publisherDtoFuture.thenApply(publishers -> Arrays.stream(publishers).map(publisherMapper::publisherResponseDtoToPublisher).toList());
    }
    public CompletableFuture<Publisher> getPublisherById(Integer publisherId){
        PublisherMapper publisherMapper = PublisherMapper.INSTANCE;
        CompletableFuture<PublisherResponseDto> publisherDtoFuture = client.apiRequest("publishers/" + publisherId, PublisherResponseDto.class);
        return publisherDtoFuture.thenApply(publisherMapper::publisherResponseDtoToPublisher);
    }
    public CompletableFuture<Publisher> createPublisher(PublisherRequestDto publisherRequestDto){
        PublisherMapper publisherMapper = PublisherMapper.INSTANCE;
        CompletableFuture<PublisherResponseDto> createPublisherDtoFuture = client.create("publishers", publisherRequestDto, PublisherResponseDto.class);
        return createPublisherDtoFuture.thenApply(publisherMapper::publisherResponseDtoToPublisher);
    }
    public CompletableFuture<Publisher> updatePublisher(Integer publisherId, PublisherRequestDto publisherRequestDto){
        PublisherMapper publisherMapper = PublisherMapper.INSTANCE;
        CompletableFuture<PublisherResponseDto> updatePublisherDtoFuture = client.update("publishers/" + publisherId, publisherRequestDto, PublisherResponseDto.class);
        return updatePublisherDtoFuture.thenApply(publisherMapper::publisherResponseDtoToPublisher);
    }
    public CompletableFuture<Void> deletePublisher(Integer publisherId){
        return client.delete("publishers/" + publisherId);
    }
}
