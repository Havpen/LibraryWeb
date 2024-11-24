package com.alisievich.Issue.mapper;

import com.alisievich.Issue.dto.IssueResponseDto;
import com.alisievich.Issue.model.Issue;
import com.alisievich.common.service.GenericMapper;
import org.mapstruct.Mapper;

@Mapper
public interface IssueMapper extends GenericMapper<Issue, IssueResponseDto> {
}
