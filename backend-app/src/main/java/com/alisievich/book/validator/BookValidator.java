package com.alisievich.book.validator;

import com.alisievich.book.model.Book;
import com.alisievich.exception.GenericErrorException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.regex.Pattern;

@Component
public class BookValidator {
    public void validate(Book book){
        String regexTitle = "^[а-яА-ЯёЁ\\s]+$";
        if(book.getTitle() == null || !Pattern.matches(regexTitle, book.getTitle())){
            throw new GenericErrorException("Поле должно содержать только буквы русского алфавита и пробелы");
        }
        int currentYear = LocalDate.now().getYear();
        if (book.getYear() == null || book.getYear() <= 0 || book.getYear() > currentYear) {
            throw new GenericErrorException("Год книги должен быть положительным и не превышать текущий год.");
        }
        String regexLanguage = "^[A-Z]{1,3}$";
        if(book.getLanguage()==null || !Pattern.matches(regexLanguage, book.getLanguage())){
            throw new GenericErrorException("Поле должно содержать только прописные буквы английского алфавита, не больше трёх.");
        }
    }
}
