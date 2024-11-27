package com.alisievich.Issue.controller;

import com.alisievich.Issue.dto.IssueRequestDto;
import com.alisievich.Issue.dto.IssueResponseDto;
import com.alisievich.Issue.mapper.IssueMapper;
import com.alisievich.Issue.model.Issue;
import com.alisievich.Issue.service.IssueService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/issues")
@AllArgsConstructor
public class IssueController {
    private final IssueService issueService;
    private final IssueMapper responseMapper;

    @Operation(summary = "Get all issues")
    @GetMapping
    public ResponseEntity<List<IssueResponseDto>> getAll(){
        return ResponseEntity.ok(issueService.getAll().stream().map(responseMapper::map).toList());
    }

    @Operation(summary = "Get issue")
    @GetMapping("/{id}")
    public ResponseEntity<IssueResponseDto> getById(@PathVariable Integer id){
        return ResponseEntity.ok(responseMapper.map(issueService.getById(id)));
    }

    @Operation(summary = "Add issue")
    @PostMapping
    public ResponseEntity<IssueResponseDto> create(@RequestBody IssueRequestDto issueRequestDto){
        Issue issue = issueService.create(issueRequestDto);
        return ResponseEntity.ok(responseMapper.map(issue));
    }

    @Operation(summary = "Update issue")
    @PutMapping("/{id}")
    public ResponseEntity<IssueResponseDto> update(@PathVariable Integer id, @RequestBody IssueRequestDto issueRequestDto){
        Issue issueModel = issueService.update(id, issueRequestDto);
        return ResponseEntity.ok(responseMapper.map(issueModel));
    }

    @Operation(summary = "Delete issue")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        issueService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
