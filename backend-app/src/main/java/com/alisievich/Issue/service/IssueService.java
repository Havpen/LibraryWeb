package com.alisievich.Issue.service;

import com.alisievich.Issue.dto.IssueRequestDto;
import com.alisievich.Issue.model.Issue;
import com.alisievich.Issue.repository.IssueRepository;
import com.alisievich.common.service.CrudService;
import com.alisievich.reader.model.Reader;
import com.alisievich.reader.repository.ReaderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class IssueService extends CrudService<Issue, Integer> {
    private final IssueRepository issueRepository;
    private final ReaderRepository readerRepository;

    public IssueService(IssueRepository issueRepository, ReaderRepository readerRepository){
        super(issueRepository);
        this.readerRepository = readerRepository;
        this.issueRepository = issueRepository;
    }
    public Issue create(IssueRequestDto requestDto){
        Reader reader = readerRepository.findById(requestDto.getReader().getId()).orElseThrow(EntityNotFoundException::new);
        Issue issueModel = Issue.builder().reader(reader).build();
        return issueRepository.save(issueModel);
    }

    public Issue update(Integer id, IssueRequestDto requestDto){
        Issue issueModel = issueRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Reader reader = readerRepository.findById(requestDto.getReader().getId()).orElseThrow(EntityNotFoundException::new);
        issueModel.setReader(reader);
        return issueRepository.save(issueModel);
    }
}
