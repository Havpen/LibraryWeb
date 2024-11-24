package com.alisievich.publisher.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alisievich.publisher.dto.PublisherRequestDto;
import com.alisievich.publisher.dto.PublisherResponseDto;
import com.alisievich.publisher.mapper.PublisherMapper;
import com.alisievich.publisher.model.Publisher;
import com.alisievich.publisher.service.PublisherService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/publishers")
@AllArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;
    private final PublisherMapper responseMapper;

    @Operation(summary = "Get all publishers")
    @GetMapping
    public ResponseEntity<List<PublisherResponseDto>> getAll(){
        return ResponseEntity.ok(publisherService.getAll().stream().map(responseMapper::map).toList());
    }

    @Operation(summary = "Get publisher")
    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponseDto> getById(@RequestParam("id") Integer id){
        return ResponseEntity.ok(responseMapper.map(publisherService.getById(id)));
    }

    @Operation(summary = "Add publisher")
    @PostMapping
    public ResponseEntity<PublisherResponseDto> create(@RequestBody PublisherRequestDto publisherRequestDto){
        Publisher publisher = publisherService.create(publisherRequestDto);
        return ResponseEntity.ok(responseMapper.map(publisher));
    }

    @Operation(summary = "Update image")
    @PutMapping("/{id}")
    public ResponseEntity<PublisherResponseDto> update(@RequestParam Integer id, @RequestBody PublisherRequestDto publisherRequestDto){
        Publisher publisher = publisherService.update(id, publisherRequestDto);
        return ResponseEntity.ok(responseMapper.map(publisher));
    }

    @Operation(summary = "Delete image")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestParam("id") Integer id){
        publisherService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
