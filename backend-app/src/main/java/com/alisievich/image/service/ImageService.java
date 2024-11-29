package com.alisievich.image.service;

import com.alisievich.exception.GenericErrorException;
import com.alisievich.image.model.Image;
import com.alisievich.image.repository.ImageRepository;
import com.alisievich.image.validator.ImageValidator;
import jakarta.persistence.EntityExistsException;
import jakarta.servlet.ServletContext;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ImageService {
    private static final String UPLOAD_DIR = "/uploads";

    private final ImageRepository imageRepository;
    private final ServletContext servletContext;
    private final ImageValidator imageValidator;

    public Resource download(Integer imageId) {
        Image image = imageRepository.findById(imageId).orElseThrow(EntityExistsException::new);

        File file = new File(servletContext.getRealPath(UPLOAD_DIR) + File.separator + image.getFilename());
        imageValidator.validate(image);
        return new FileSystemResource(file);
    }

    public Image upload(MultipartFile file) {
        // Create the uploads directory if it doesn't exist
        File directory = new File(servletContext.getRealPath(UPLOAD_DIR));
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Create the file on the server
        String extension = "";
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null) {
            int extensionPos = originalFilename.lastIndexOf(".");
            if (extensionPos != -1) {
                extension = originalFilename.substring(extensionPos + 1);
            }
        }

        String filename = UUID.randomUUID() + "." + extension;
        File serverFile = new File(directory.getAbsolutePath() + File.separator + filename);

        try {
            file.transferTo(serverFile);
        } catch (IOException ex) {
            throw new GenericErrorException("Failed to save image on disk! Error: " + ex.getMessage());
        }

        Image image = Image.builder()
                .filename(filename)
                .build();
        imageValidator.validate(image);
        return imageRepository.save(image);
    }
}
