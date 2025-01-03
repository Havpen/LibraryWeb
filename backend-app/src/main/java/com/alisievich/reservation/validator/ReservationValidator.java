package com.alisievich.reservation.validator;

import com.alisievich.exception.GenericErrorException;
import com.alisievich.reservation.model.Reservation;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Pattern;

@Component
public class ReservationValidator {
    public void validate(Reservation reservation){
        String regexDate = "^\\d{2}\\.\\d{2}\\.\\d{4}$";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        String reservationString = reservation.getReservationDate().format(formatter);
        if(reservation.getReservationDate() == null || !Pattern.matches(regexDate, reservationString)){
            throw new GenericErrorException("Поле должно быть в формате dd.MM.yyyy.");
        }

        String reservationDeadLineString = reservation.getReservationDeadLine().format(formatter);
        if(reservation.getReservationDeadLine() == null || !Pattern.matches(regexDate, reservationDeadLineString)){
            throw new GenericErrorException("Поле должно быть в формате dd.MM.yyyy.");
        }

        if (!(Objects.equals(reservation.getStatus(), "CONFIRMED") || Objects.equals(reservation.getStatus(), "CANCELLED"))) {
            throw new GenericErrorException("Статус должен быть CONFIRMED или CANCELLED!");
        }
    }
}
