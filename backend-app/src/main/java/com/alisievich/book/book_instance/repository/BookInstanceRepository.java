package com.alisievich.book.book_instance.repository;

import com.alisievich.book.book_instance.model.BookInstance;
import org.springframework.data.repository.CrudRepository;

public interface BookInstanceRepository extends CrudRepository<BookInstance, Integer> {
}
