package com.alisievich.Issue.dto;

import com.alisievich.book.book_instance.dto.BookInstanceResponseDto;
import com.alisievich.reader.dto.ReaderResponseDto;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IssueResponseDto {
    private Integer id;
    private ReaderResponseDto reader;
    private LocalDate reservationDate;
    private LocalDate reservationDeadLine;
    private LocalDate returnDate;
    private BookInstanceResponseDto bookInstance;
}
