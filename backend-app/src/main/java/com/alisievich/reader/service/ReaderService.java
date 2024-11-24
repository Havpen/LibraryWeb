package com.alisievich.reader.service;

import com.alisievich.common.service.CrudService;
import com.alisievich.reader.dto.ReaderRequestDto;
import com.alisievich.reader.model.Reader;
import com.alisievich.reader.repository.ReaderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class ReaderService extends CrudService<Reader, Integer> {
    private final ReaderRepository readerRepository;

    public ReaderService(ReaderRepository readerRepository){
        super(readerRepository);
        this.readerRepository = readerRepository;
    }

    public Reader create(ReaderRequestDto requestDto){
        Reader readerModel = Reader.builder().name(requestDto.getName()).build();
        return readerRepository.save(readerModel);
    }

    public Reader update(Integer id, ReaderRequestDto requestDto){
        Reader readerModel = readerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        readerModel.setName(requestDto.getName());
        return readerRepository.save(readerModel);
    }
}
