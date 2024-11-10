package com.alisievich.author.controller;

import com.alisievich.author.dto.AuthorRequestDto;
import com.alisievich.author.dto.AuthorResponseDto;
import com.alisievich.author.mapper.AuthorMapper;
import com.alisievich.author.model.Author;
import com.alisievich.author.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/authors")
@AllArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorMapper responseMapper;

    @Operation(summary = "Get all authors")
    @GetMapping
    public ResponseEntity<List<AuthorResponseDto>> getAll() {
        return ResponseEntity.ok(authorService.getAll().stream().map(responseMapper::map).toList());
    }

    @Operation(summary = "Get author")
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> getById(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(responseMapper.map(authorService.getById(id)));
    }

    @Operation(summary = "Add author")
    @PostMapping
    public ResponseEntity<AuthorResponseDto> create(@RequestBody AuthorRequestDto authorRequestDto) {
        Author author = authorService.create(authorRequestDto);
        return ResponseEntity.ok(responseMapper.map(author));
    }

    @Operation(summary = "Update author")
    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> update(@RequestParam("id") Integer id,
                                                    @RequestBody AuthorRequestDto authorRequestDto) {
        Author author = authorService.update(id, authorRequestDto);
        return ResponseEntity.ok(responseMapper.map(author));
    }

    @Operation(summary = "Delete author")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestParam("id") Integer id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
