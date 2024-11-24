package com.alisievich.image.controller;

import com.alisievich.image.dto.ImageRequestDto;
import com.alisievich.image.dto.ImageResponseDto;
import com.alisievich.image.mapper.ImageMapper;
import com.alisievich.image.model.Image;
import com.alisievich.image.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/images")
@AllArgsConstructor
public class ImageController {
    private final ImageService imageService;
    private final ImageMapper responseMapper;

    @Operation(summary = "Get all images")
    @GetMapping
    public ResponseEntity<List<ImageResponseDto>> getAll() {
        return ResponseEntity.ok(imageService.getAll().stream().map(responseMapper::map).toList());
    }

    @Operation(summary = "Get image")
    @GetMapping("/{id}")
    public ResponseEntity<ImageResponseDto> getById(@RequestParam("id") Integer id){
        return ResponseEntity.ok(responseMapper.map(imageService.getById(id)));
    }

    @Operation(summary = "Add image")
    @PostMapping
    public ResponseEntity<ImageResponseDto> create(@RequestBody ImageRequestDto imageRequestDto){
        Image image = imageService.create(imageRequestDto);
        return ResponseEntity.ok(responseMapper.map(image));
    }

    @Operation(summary = "Update image")
    @PutMapping("/{id}")
    public ResponseEntity<ImageResponseDto> update(@RequestParam("id") Integer id, @RequestBody ImageRequestDto imageRequestDto){
        Image image = imageService.update(id, imageRequestDto);
        return ResponseEntity.ok(responseMapper.map(image));
    }

    @Operation(summary = "Delete image")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestParam("id") Integer id){
        imageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
