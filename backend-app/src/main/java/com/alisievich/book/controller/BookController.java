package com.alisievich.book.controller;

import com.alisievich.book.dto.BookDto;
import com.alisievich.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/books")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @Operation(summary = "Get all books")
    @GetMapping
    public ResponseEntity<List<BookDto>> getAll(){
        return ResponseEntity.ok(bookService.getAll());
    }
}
