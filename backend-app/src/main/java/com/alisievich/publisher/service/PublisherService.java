package com.alisievich.publisher.service;

import com.alisievich.common.service.CrudService;
import com.alisievich.publisher.dto.PublisherRequestDto;
import com.alisievich.publisher.model.Publisher;
import com.alisievich.publisher.repository.PublisherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PublisherService extends CrudService<Publisher, Integer> {
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository){
        super(publisherRepository);
        this.publisherRepository = publisherRepository;
    }
    public Publisher create(PublisherRequestDto requestDto){
        Publisher publisher = Publisher.builder()
                .name(requestDto.getName())
                .address(requestDto.getAddress())
                .build();
        return publisherRepository.save(publisher);
    }

    public Publisher update(Integer id, PublisherRequestDto requestDto){
        Publisher publisher = publisherRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        publisher.setName(requestDto.getName());
        publisher.setAddress(requestDto.getAddress());
        return publisherRepository.save(publisher);
    }
}
