package com.alisievich.Issue.validator;

import com.alisievich.Issue.model.Issue;
import com.alisievich.exception.GenericErrorException;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@Component
public class IssueValidator {

    public void validate(Issue issue) {
        String regexDate = "^\\d{2}\\.\\d{2}\\.\\d{4}$";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        String reservationDateString = issue.getReservationDate().format(formatter);
        if (issue.getReservationDate() == null || !Pattern.matches(regexDate, reservationDateString)) {
            throw new GenericErrorException("Поле 'reservationDate' должно быть в формате dd.MM.yyyy.");
        }

        String reservationDeadLineString = issue.getReservationDeadLine().format(formatter);
        if (issue.getReservationDeadLine() == null || !Pattern.matches(regexDate, reservationDeadLineString)) {
            throw new GenericErrorException("Поле 'reservationDeadLine' должно быть в формате dd.MM.yyyy.");
        }

        String returnDateString = issue.getReturnDate().format(formatter);
        if (issue.getReturnDate() == null || !Pattern.matches(regexDate, returnDateString)) {
            throw new GenericErrorException("Поле 'returnDate' должно быть в формате dd.MM.yyyy.");
        }

        if (issue.getReturnDate().isBefore(issue.getReservationDate())) {
            throw new GenericErrorException("Поле 'returnDate' не может быть раньше 'reservationDate'.");
        }

        if (issue.getReservationDeadLine().isBefore(issue.getReservationDate())) {
            throw new GenericErrorException("Поле 'reservationDeadLine' не может быть раньше 'reservationDate'.");
        }
    }
}
