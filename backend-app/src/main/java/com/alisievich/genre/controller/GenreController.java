package com.alisievich.genre.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alisievich.genre.dto.GenreRequestDto;
import com.alisievich.genre.dto.GenreResponseDto;
import com.alisievich.genre.mapper.GenreMapper;
import com.alisievich.genre.model.Genre;
import com.alisievich.genre.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/genres")
@AllArgsConstructor
public class GenreController {
    private final GenreService genreService;
    private final GenreMapper responseMapper;

    @Operation(summary = "Get all genres")
    @GetMapping
    public ResponseEntity<List<GenreResponseDto>> getAll(){
        return ResponseEntity.ok(genreService.getAll().stream().map(responseMapper::map).toList());
    }

    @Operation(summary = "Get genre")
    @GetMapping("/{id}")
    public ResponseEntity<GenreResponseDto> getById(@RequestParam("id") Integer id){
        return ResponseEntity.ok(responseMapper.map(genreService.getById(id)));
    }

    @Operation(summary = "Add genre")
    @PostMapping
    public ResponseEntity<GenreResponseDto> create(@RequestBody GenreRequestDto genreRequestDto){
        Genre genre = genreService.create(genreRequestDto);
        return ResponseEntity.ok(responseMapper.map(genre));
    }

    @Operation(summary = "Update genre")
    @PutMapping("/{id}")
    public ResponseEntity<GenreResponseDto> update(@RequestParam("id") Integer id, @RequestBody GenreRequestDto genreRequestDto){
        Genre genre = genreService.update(id, genreRequestDto);
        return ResponseEntity.ok(responseMapper.map(genre));
    }

    @Operation(summary = "Delete author")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestParam("id") Integer id){
        genreService.delete(id);
        return  ResponseEntity.noContent().build();
    }
}
