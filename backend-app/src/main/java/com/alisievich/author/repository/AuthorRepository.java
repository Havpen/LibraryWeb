package com.alisievich.author.repository;

import com.alisievich.author.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
