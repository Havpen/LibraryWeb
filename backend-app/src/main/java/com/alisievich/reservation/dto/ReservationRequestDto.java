package com.alisievich.reservation.dto;

import com.alisievich.book.book_instance.dto.BookInstanceRequestDto;
import com.alisievich.reader.dto.ReaderRequestDto;
import com.alisievich.reader.model.Reader;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationRequestDto {
    private Integer id;
    private ReaderRequestDto reader;
    private LocalDate reservationDate;
    private LocalDate reservationDeadLine;
    private String status;
    private BookInstanceRequestDto bookInstance;
}
