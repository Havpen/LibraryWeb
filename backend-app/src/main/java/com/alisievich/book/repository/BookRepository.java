package com.alisievich.book.repository;

import com.alisievich.book.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, String> {
}
