package com.alisievich.reader.controller;

import com.alisievich.reader.dto.ReaderRequestDto;
import com.alisievich.reader.dto.ReaderResponseDto;
import com.alisievich.reader.mapper.ReaderMapper;
import com.alisievich.reader.model.Reader;
import com.alisievich.reader.service.ReaderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/readers")
@AllArgsConstructor
public class ReaderController {
    private final ReaderService readerService;
    private final ReaderMapper responseMapper;

    @Operation(summary = "Get all readers")
    @GetMapping
    public ResponseEntity<List<ReaderResponseDto>> getAll(){
        return ResponseEntity.ok(readerService.getAll().stream().map(responseMapper::map).toList());
    }

    @Operation(summary = "Get reader")
    @GetMapping("/{id}")
    public ResponseEntity<ReaderResponseDto> getById(@PathVariable Integer id){
        return ResponseEntity.ok(responseMapper.map(readerService.getById(id)));
    }

    @Operation(summary = "Add reader")
    @PostMapping
    public ResponseEntity<ReaderResponseDto> create(@RequestBody ReaderRequestDto readerRequestDto){
        Reader reader = readerService.create(readerRequestDto);
        return ResponseEntity.ok(responseMapper.map(reader));
    }

    @Operation(summary = "Update reader")
    @PutMapping("/{id}")
    public ResponseEntity<ReaderResponseDto> update(@PathVariable Integer id, @RequestBody ReaderRequestDto readerRequestDto){
        Reader reader = readerService.update(id, readerRequestDto);
        return ResponseEntity.ok(responseMapper.map(reader));
    }

    @Operation(summary = "Delete reader")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        readerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
