package com.alisievich.author.service;

import com.alisievich.author.dto.AuthorRequestDto;
import com.alisievich.author.dto.AuthorResponseDto;
import com.alisievich.author.mapper.AuthorMapper;
import com.alisievich.author.model.Author;
import com.alisievich.author.repository.AuthorRepository;
import com.alisievich.common.service.CrudService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends CrudService<Author, Integer, AuthorResponseDto> {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository,
                         AuthorMapper responseMapper) {
        super(authorRepository, responseMapper);

        this.authorRepository = authorRepository;
    }

    public Author getById(Integer id) {
        return authorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Author create(AuthorRequestDto requestDto) {
        Author author = Author.builder()
                .name(requestDto.getName())
                .build();
        return authorRepository.save(author);
    }

    public Author update(Integer id, AuthorRequestDto requestDto) {
        Author author = authorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        author.setName(requestDto.getName());
        return authorRepository.save(author);
    }

    public void delete(Integer id) {
        authorRepository.deleteById(id);
    }
}
