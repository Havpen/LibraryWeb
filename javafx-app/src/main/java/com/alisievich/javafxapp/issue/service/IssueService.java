package com.alisievich.javafxapp.issue.service;

import com.alisievich.javafxapp.client.BackendClient;
import com.alisievich.javafxapp.issue.dto.IssueRequestDto;
import com.alisievich.javafxapp.issue.dto.IssueResponseDto;
import com.alisievich.javafxapp.issue.mapper.IssueMapper;
import com.alisievich.javafxapp.issue.model.Issue;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class IssueService {
    private BackendClient client;

    public IssueService(){
        client = BackendClient.getInstance();
    }

    public CompletableFuture<List<Issue>> getAllIssues(){
        IssueMapper issueMapper = IssueMapper.INSTANCE;
        CompletableFuture<IssueResponseDto[]> issueDtoFuture = client.apiRequest("issues", IssueResponseDto[].class);
        return issueDtoFuture.thenApply(issues-> Arrays.stream(issues).map(issueMapper::issueResponseDtoIssue).toList());
    }

    public CompletableFuture<Issue> getIssueById(Integer issueId){
        IssueMapper issueMapper = IssueMapper.INSTANCE;
        CompletableFuture<IssueResponseDto> issueDtoFuture = client.apiRequest("issues/" + issueId, IssueResponseDto.class);
        return issueDtoFuture.thenApply(issueMapper::issueResponseDtoIssue);
    }

    public CompletableFuture<Issue> createIssue(IssueRequestDto issueRequestDto){
        IssueMapper issueMapper = IssueMapper.INSTANCE;
        CompletableFuture<IssueResponseDto> createIssueDtoFuture = client.create("issues", issueRequestDto, IssueResponseDto.class);
        return createIssueDtoFuture.thenApply(issueMapper::issueResponseDtoIssue);
    }

    public CompletableFuture<Issue> updateIssue(Integer issueId, IssueRequestDto issueRequestDto){
        IssueMapper issueMapper = IssueMapper.INSTANCE;
        CompletableFuture<IssueResponseDto> updateIssueDtoFuture = client.update("issues/" + issueId, issueRequestDto, IssueResponseDto.class);
        return updateIssueDtoFuture.thenApply(issueMapper::issueResponseDtoIssue);
    }

    public CompletableFuture<Void> deleteIssue(Integer issueId){
        return client.delete("issues/" + issueId);
    }
}
