package com.alisievich.javafxapp.reservation.model;

import com.alisievich.javafxapp.book.book_instance.model.BookInstance;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private Integer id;
    private LocalDate reservationDate;
    private LocalDate reservationDeadLine;
    private String status;
    private BookInstance bookInstance;
}
