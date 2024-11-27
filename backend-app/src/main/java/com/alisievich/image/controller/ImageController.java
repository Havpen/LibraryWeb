package com.alisievich.image.controller;

import com.alisievich.image.dto.ImageResponseDto;
import com.alisievich.image.mapper.ImageMapper;
import com.alisievich.image.model.Image;
import com.alisievich.image.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/images")
@AllArgsConstructor
public class ImageController {
    private final ImageService imageService;
    private final ImageMapper responseMapper;

    @Operation(summary = "Download image")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Resource> upload(@PathVariable Integer id){
        Resource imageResource = imageService.download(id);

        // Detect content length
        long contentLength = 0L;
        try {
            contentLength = imageResource.contentLength();
        } catch (IOException ex) {
            System.err.println("Failed to detect content length of resource! Error: " + ex.getMessage());
        }

        // Set the content disposition header to indicate a file attachment
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imageResource.getFilename() + "\"");

        // Return the file as a response entity
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(contentLength)
                .contentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM)
                .body(imageResource);
    }

    @Operation(summary = "Upload image")
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ImageResponseDto> upload(@RequestParam("file") MultipartFile imageFile){
        Image image = imageService.upload(imageFile);
        return ResponseEntity.ok(responseMapper.map(image));
    }
}
