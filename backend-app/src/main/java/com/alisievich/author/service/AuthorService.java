package com.alisievich.author.service;

import com.alisievich.author.dto.AuthorResponseDto;
import com.alisievich.author.mapper.AuthorMapper;
import com.alisievich.author.model.Author;
import com.alisievich.author.repository.AuthorRepository;
import com.alisievich.common.service.CrudService;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends CrudService<Author, Integer, AuthorResponseDto> {
    public AuthorService(AuthorRepository authorRepository,
                         AuthorMapper responseMapper) {
        super(authorRepository, responseMapper);
    }
}
