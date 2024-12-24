package com.alisievich.javafxapp.reservation.dto;

import com.alisievich.javafxapp.book.book_instance.model.BookInstance;
import com.alisievich.javafxapp.reader.dto.ReaderRequestDto;
import lombok.*;
import java.time.LocalDate;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestDto {
    private Integer id;
    private ReaderRequestDto reader;
    private LocalDate reservationDate;
    private LocalDate reservationDeadLine;
    private String status;
    private BookInstance bookInstance;
}
