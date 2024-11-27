package com.alisievich.book.controller;

import com.alisievich.book.dto.BookDto;
import com.alisievich.book.dto.BookRequestDto;
import com.alisievich.book.dto.BookResponseDto;
import com.alisievich.book.mapper.BookMapper;
import com.alisievich.book.model.Book;
import com.alisievich.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/books")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookMapper responseMapper;

    @Operation(summary = "Get all books")
    @GetMapping
    public ResponseEntity<List<BookDto>> getAll(){
        return ResponseEntity.ok(bookService.getAll());
    }

    @Operation(summary = "Get reader")
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getById(@PathVariable Integer id){
        return ResponseEntity.ok(responseMapper.map(bookService.getById(id)));
    }

    @Operation(summary = "Add reader")
    @PostMapping
    public ResponseEntity<BookResponseDto> create(@RequestBody BookRequestDto requestDto){
        Book book = bookService.create(requestDto);
        return ResponseEntity.ok(responseMapper.map(book));
    }

    @Operation(summary = "Update reader")
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> update(@PathVariable Integer id, @RequestBody BookRequestDto requestDto){
        Book book = bookService.update(id, requestDto);
        return ResponseEntity.ok(responseMapper.map(book));
    }

    @Operation(summary = "Delete reader")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
