package com.alisievich.reservation.dto;

import com.alisievich.book.book_instance.dto.BookInstanceResponseDto;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationResponseDto {
    private Integer id;
    private LocalDate reservationDate;
    private LocalDate reservationDeadLine;
    private String status;
    private BookInstanceResponseDto bookInstance;
}
