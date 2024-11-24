package com.alisievich.publisher.mapper;

import com.alisievich.common.service.GenericMapper;
import com.alisievich.publisher.dto.PublisherResponseDto;
import com.alisievich.publisher.model.Publisher;
import org.mapstruct.Mapper;

@Mapper
public interface PublisherMapper extends GenericMapper<Publisher, PublisherResponseDto> {
}
