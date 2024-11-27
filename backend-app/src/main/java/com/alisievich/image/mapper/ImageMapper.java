package com.alisievich.image.mapper;

import com.alisievich.image.dto.ImageResponseDto;
import com.alisievich.image.model.Image;
import org.mapstruct.Mapper;

@Mapper
public interface ImageMapper {
    ImageResponseDto map(Image image);
}
