package com.alisievich.book.book_instance.controller;

import com.alisievich.book.book_instance.dto.BookInstanceRequestDto;
import com.alisievich.book.book_instance.dto.BookInstanceResponseDto;
import com.alisievich.book.book_instance.mapper.BookInstanceMapper;
import com.alisievich.book.book_instance.model.BookInstance;
import com.alisievich.book.book_instance.service.BookInstanceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/bookInstances")
@AllArgsConstructor
public class BookInstanceController {
    private final BookInstanceService bookInstanceService;
    private final BookInstanceMapper responseMapper;

    @Operation(summary = "Get all bookInstances")
    @GetMapping
    public ResponseEntity<List<BookInstanceResponseDto>> getAll(){
        return ResponseEntity.ok(bookInstanceService.getAll().stream().map(responseMapper::map).toList());
    }

    @Operation(summary = "Get bookInstance")
    @GetMapping("/{id}")
    public ResponseEntity<BookInstanceResponseDto> getById(@PathVariable Integer id){
        return  ResponseEntity.ok(responseMapper.map(bookInstanceService.getById(id)));
    }

    @Operation(summary = "Add bookInstance")
    @PostMapping
    public ResponseEntity<BookInstanceResponseDto> create(@RequestBody BookInstanceRequestDto bookInstanceRequestDto){
        BookInstance bookInstance = bookInstanceService.create(bookInstanceRequestDto);
        return ResponseEntity.ok(responseMapper.map(bookInstance));
    }

    @Operation(summary = "Update bookInstance")
    @PutMapping("/{id}")
    public ResponseEntity<BookInstanceResponseDto> update(@PathVariable Integer id, @RequestBody BookInstanceRequestDto bookInstanceRequestDto){
        BookInstance bookInstance = bookInstanceService.update(id, bookInstanceRequestDto);
        return ResponseEntity.ok(responseMapper.map(bookInstance));
    }

    @Operation(summary = "Delete bookInstance")
    @DeleteMapping("/{id}")
    public ResponseEntity<BookInstanceResponseDto> delete(@PathVariable Integer id){
        bookInstanceService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
