package com.alisievich.image.repository;

import com.alisievich.image.model.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Integer> {
}
