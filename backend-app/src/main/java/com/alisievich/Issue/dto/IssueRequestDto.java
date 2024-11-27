package com.alisievich.Issue.dto;

import com.alisievich.book.book_instance.dto.BookInstanceRequestDto;
import com.alisievich.reader.dto.ReaderRequestDto;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IssueRequestDto {
    private Integer id;
    private ReaderRequestDto reader;
    private LocalDate reservationDate;
    private LocalDate reservationDeadLine;
    private LocalDate returnDate;
    private BookInstanceRequestDto bookInstance;
}
