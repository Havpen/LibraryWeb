package com.alisievich.javafxapp.reservation.dto;

import com.alisievich.javafxapp.book.book_instance.model.BookInstance;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseDto {
    private Integer id;
    private LocalDate reservationDate;
    private LocalDate reservationDeadLine;
    private String status;
    private BookInstance bookInstance;
}
