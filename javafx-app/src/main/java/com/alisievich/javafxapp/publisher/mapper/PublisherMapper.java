package com.alisievich.javafxapp.publisher.mapper;

import com.alisievich.javafxapp.publisher.dto.PublisherResponseDto;
import com.alisievich.javafxapp.publisher.model.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PublisherMapper {
    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);
    Publisher publisherResponseDtoToPublisher(PublisherResponseDto responseDto);
}
