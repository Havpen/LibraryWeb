package com.alisievich.reservation.dto;

import com.alisievich.book.book_instance.dto.BookInstanceResponseDto;
import com.alisievich.reader.dto.ReaderResponseDto;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationResponseDto {
    private Integer id;
    private ReaderResponseDto reader;
    private LocalDate reservationDate;
    private LocalDate reservationDeadLine;
    private String status;
    private BookInstanceResponseDto bookInstance;
}
