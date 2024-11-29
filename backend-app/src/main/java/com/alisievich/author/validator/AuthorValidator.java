package com.alisievich.author.validator;

import com.alisievich.author.model.Author;
import com.alisievich.exception.GenericErrorException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class AuthorValidator {
    public void validate(Author author){
        String regex = "^[а-яА-ЯёЁ\\s]+$";
        if(author.getName() == null || !Pattern.matches(regex, author.getName())){
            throw new GenericErrorException("Поле должно содержать только русские буквы и пробелы");
        }
    }
}
