package com.alisievich.reader.mapper;

import com.alisievich.common.service.GenericMapper;
import com.alisievich.reader.dto.ReaderResponseDto;
import com.alisievich.reader.model.Reader;
import org.mapstruct.Mapper;

@Mapper
public interface ReaderMapper extends GenericMapper<Reader, ReaderResponseDto> {
}
