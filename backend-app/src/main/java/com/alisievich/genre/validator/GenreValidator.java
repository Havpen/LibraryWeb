package com.alisievich.genre.validator;

import com.alisievich.exception.GenericErrorException;
import com.alisievich.genre.model.Genre;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class GenreValidator {
    public void validate(Genre genre){
        String regex = "^[а-яА-ЯёЁ\\s]+$";
        if(genre.getName() == null || !Pattern.matches(regex, genre.getName())){
            throw new GenericErrorException("Поле должно содержать только буквы русского алфавита и пробелы");
        }
    }
}
