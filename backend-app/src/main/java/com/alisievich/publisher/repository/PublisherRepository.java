package com.alisievich.publisher.repository;

import org.springframework.data.repository.CrudRepository;
import com.alisievich.publisher.model.Publisher;

public interface PublisherRepository extends CrudRepository<Publisher, Integer> {
}
