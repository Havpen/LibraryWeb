package com.alisievich.image.validator;


import com.alisievich.exception.GenericErrorException;
import com.alisievich.image.model.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageValidator {
    public void validate(Image image) {
        if (image.getFilename() == null || image.getFilename().isEmpty()) {
            throw new GenericErrorException("Это поле не может быть пустым.");
        }

        String regex = "([^\\s]+(\\.(?i)(jpg|jpeg|png))$)";
        if (!image.getFilename().matches(regex)) {
            throw new GenericErrorException("Неверный формат файла.");
        }
    }
}
