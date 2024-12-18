package com.alisievich.javafxapp.issue.mapper;

import com.alisievich.javafxapp.issue.dto.IssueResponseDto;
import com.alisievich.javafxapp.issue.model.Issue;
import com.alisievich.javafxapp.reader.dto.ReaderResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IssueMapper {
    IssueMapper INSTANCE = Mappers.getMapper(IssueMapper.class);
    Issue issueResponseDtoIssue(IssueResponseDto responseDto);
}
