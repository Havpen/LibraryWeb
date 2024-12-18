package com.alisievich.javafxapp.reader.mapper;

import com.alisievich.javafxapp.reader.dto.ReaderResponseDto;
import com.alisievich.javafxapp.reader.model.Reader;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReaderMapper {
    ReaderMapper INSTANCE = Mappers.getMapper(ReaderMapper.class);
    Reader readerResponseDtoToReader(ReaderResponseDto responseDto);
}
