package com.alisievich.reader.validator;

import com.alisievich.exception.GenericErrorException;
import com.alisievich.reader.model.Reader;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@Component
public class ReaderValidator {

    public void validate(Reader reader) {
        String regexName = "^[а-яА-ЯёЁ\\s]+$";
        if (reader.getName() == null || !Pattern.matches(regexName, reader.getName())) {
            throw new GenericErrorException("Поле 'name' должно содержать только русские буквы и пробелы.");
        }

        String regexAddress = "^[А-Яа-яЁё0-9\\s]+$";
        if (reader.getAddress() == null || !Pattern.matches(regexAddress, reader.getAddress())) {
            throw new GenericErrorException("Поле 'address' должно содержать только русские буквы, цифры и пробелы.");
        }

        String regexPhone = "^\\+\\d+$";
        if (reader.getPhone() == null || !Pattern.matches(regexPhone, reader.getPhone())) {
            throw new GenericErrorException("Поле 'phone' должно начинаться с '+' и содержать только цифры.");
        }

        String regexDate = "^\\d{2}\\.\\d{2}\\.\\d{4}$";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        String birthdayString = reader.getBirthday().format(formatter); // Преобразуем LocalDate в строку
        if (reader.getBirthday() == null || !Pattern.matches(regexDate, birthdayString)) {
            throw new GenericErrorException("Поле 'birthday' должно быть в формате dd.MM.yyyy.");
        }

        String registrationDateString = reader.getRegistrationDate().format(formatter); // Преобразуем LocalDate в строку
        if (reader.getRegistrationDate() == null || !Pattern.matches(regexDate, registrationDateString)) {
            throw new GenericErrorException("Поле 'registrationDate' должно быть в формате dd.MM.yyyy.");
        }
    }
}
