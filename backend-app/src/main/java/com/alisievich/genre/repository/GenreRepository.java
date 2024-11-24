package com.alisievich.genre.repository;

import com.alisievich.genre.model.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Integer> {
}
