package com.alisievich.image.service;

import com.alisievich.common.service.CrudService;
import com.alisievich.image.dto.ImageRequestDto;
import com.alisievich.image.model.Image;
import com.alisievich.image.repository.ImageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ImageService extends CrudService<Image, Integer> {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository){
        super(imageRepository);

        this.imageRepository = imageRepository;
    }

    public Image create(ImageRequestDto requestDto){
        Image image = Image.builder().filename(requestDto.getFilename()).build();
        return save(image);
    }

    public Image update(Integer id, ImageRequestDto requestDto){
        Image image = imageRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        image.setFilename(requestDto.getFilename());
        return save(image);
    }
}
