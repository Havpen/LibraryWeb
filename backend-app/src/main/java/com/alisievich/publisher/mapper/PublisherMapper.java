package com.alisievich.publisher.mapper;

import com.alisievich.common.service.GenericMapper;
import com.alisievich.publisher.dto.PublisherIdDto;
import com.alisievich.publisher.dto.PublisherResponseDto;
import com.alisievich.publisher.model.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PublisherMapper extends GenericMapper<Publisher, PublisherResponseDto> {
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "address", ignore = true)
    Publisher map(PublisherIdDto idDto);
}
