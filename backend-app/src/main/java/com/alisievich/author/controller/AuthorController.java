package com.alisievich.author.controller;

import com.alisievich.author.dto.AuthorResponseDto;
import com.alisievich.author.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/authors")
@AllArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @Operation(summary = "Get all authors")
    @GetMapping
    public ResponseEntity<List<AuthorResponseDto>> getAll(){
        return ResponseEntity.ok(authorService.getAll());
    }
}
