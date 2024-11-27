package com.alisievich.Issue.service;

import com.alisievich.Issue.dto.IssueRequestDto;
import com.alisievich.Issue.model.Issue;
import com.alisievich.Issue.repository.IssueRepository;
import com.alisievich.book.book_instance.model.BookInstance;
import com.alisievich.book.book_instance.repository.BookInstanceRepository;
import com.alisievich.common.service.CrudService;
import com.alisievich.reader.model.Reader;
import com.alisievich.reader.repository.ReaderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class IssueService extends CrudService<Issue, Integer> {
    private final IssueRepository issueRepository;
    private final ReaderRepository readerRepository;
    private final BookInstanceRepository bookInstanceRepository;

    public IssueService(IssueRepository issueRepository, ReaderRepository readerRepository, BookInstanceRepository bookInstanceRepository){
        super(issueRepository);
        this.readerRepository = readerRepository;
        this.issueRepository = issueRepository;
        this.bookInstanceRepository = bookInstanceRepository;
    }
    public Issue create(IssueRequestDto requestDto){
        Reader reader = readerRepository.findById(requestDto.getReader().getId()).orElseThrow(EntityNotFoundException::new);
        BookInstance bookInstance = bookInstanceRepository.findById(requestDto.getBookInstance().getId()).orElseThrow(EntityNotFoundException::new);
        Issue issueModel = Issue.builder()
                .reader(reader)
                .reservationDate(requestDto.getReservationDate())
                .reservationDeadLine(requestDto.getReservationDeadLine())
                .returnDate(requestDto.getReturnDate())
                .bookInstance(bookInstance)
                .build();
        return issueRepository.save(issueModel);
    }

    public Issue update(Integer id, IssueRequestDto requestDto){
        Issue issue = issueRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Reader reader = readerRepository.findById(requestDto.getReader().getId()).orElseThrow(EntityNotFoundException::new);
        BookInstance bookInstance = bookInstanceRepository.findById(requestDto.getBookInstance().getId()).orElseThrow(EntityNotFoundException::new);
        issue.setReader(reader);
        issue.setReservationDate(requestDto.getReservationDate());
        issue.setReservationDeadLine(requestDto.getReservationDeadLine());
        issue.setReturnDate(requestDto.getReturnDate());
        issue.setBookInstance(bookInstance);
        return issueRepository.save(issue);
    }
}
