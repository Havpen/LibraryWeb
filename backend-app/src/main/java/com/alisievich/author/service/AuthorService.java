package com.alisievich.author.service;

import com.alisievich.author.dto.AuthorRequestDto;
import com.alisievich.author.model.Author;
import com.alisievich.author.repository.AuthorRepository;
import com.alisievich.author.validator.AuthorValidator;
import com.alisievich.common.service.CrudService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends CrudService<Author, Integer> {
    private final AuthorRepository authorRepository;
    private final AuthorValidator authorValidator;

    public AuthorService(AuthorRepository authorRepository, AuthorValidator authorValidator) {
        super(authorRepository);

        this.authorRepository = authorRepository;
        this.authorValidator = authorValidator;
    }

    public Author create(AuthorRequestDto requestDto) {
        Author author = Author.builder()
                .name(requestDto.getName())
                .build();
        authorValidator.validate(author);
        return save(author);
    }

    public Author update(Integer id, AuthorRequestDto requestDto) {
        Author author = authorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        author.setName(requestDto.getName());
        authorValidator.validate(author);
        return save(author);
    }
}
