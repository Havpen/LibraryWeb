package com.alisievich.reader.service;

import com.alisievich.common.service.CrudService;
import com.alisievich.reader.dto.ReaderRequestDto;
import com.alisievich.reader.model.Reader;
import com.alisievich.reader.repository.ReaderRepository;
import com.alisievich.reader.validator.ReaderValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ReaderService extends CrudService<Reader, Integer> {
    private final ReaderRepository readerRepository;
    private final ReaderValidator readerValidator;

    public ReaderService(ReaderRepository readerRepository, ReaderValidator readerValidator){
        super(readerRepository);
        this.readerValidator = readerValidator;
        this.readerRepository = readerRepository;
    }

    public Reader create(ReaderRequestDto requestDto){
        Reader reader = Reader.builder()
                .name(requestDto.getName())
                .address(requestDto.getAddress())
                .birthday(requestDto.getBirthday())
                .email(requestDto.getEmail())
                .cardNumber(requestDto.getCardNumber())
                .registrationDate(requestDto.getRegistrationDate())
                .phone(requestDto.getPhone())
                .build();
        readerValidator.validate(reader);
        return readerRepository.save(reader);
    }

    public Reader update(Integer id, ReaderRequestDto requestDto){
        Reader reader = readerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        reader.setName(requestDto.getName());
        reader.setAddress(requestDto.getAddress());
        reader.setBirthday(requestDto.getBirthday());
        reader.setEmail(requestDto.getEmail());
        reader.setCardNumber(requestDto.getCardNumber());
        reader.setRegistrationDate(requestDto.getRegistrationDate());
        reader.setPhone(requestDto.getPhone());
        readerValidator.validate(reader);
        return readerRepository.save(reader);
    }
}
