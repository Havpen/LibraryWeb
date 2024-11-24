package com.alisievich.genre.service;

import com.alisievich.common.service.CrudService;
import com.alisievich.genre.dto.GenreRequestDto;
import com.alisievich.genre.model.Genre;
import com.alisievich.genre.repository.GenreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GenreService extends CrudService<Genre, Integer> {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository){
        super(genreRepository);

        this.genreRepository = genreRepository;
    }

    public Genre create(GenreRequestDto requestDto){
        Genre genre = Genre.builder().name(requestDto.getName()).build();
        return save(genre);
    }

    public Genre update(Integer id, GenreRequestDto requestDto){
        Genre genre =  genreRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        genre.setName(requestDto.getName());
        return save(genre);
    }
}
