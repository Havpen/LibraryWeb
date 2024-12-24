package com.alisievich.javafxapp.issue.dto;

import com.alisievich.javafxapp.book.book_instance.model.BookInstance;
import com.alisievich.javafxapp.reader.model.Reader;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IssueResponseDto {
    private Integer id;
    private Reader reader;
    private LocalDate reservationDate;
    private LocalDate reservationDeadLine;
    private LocalDate returnDate;
    private BookInstance bookInstance;
}