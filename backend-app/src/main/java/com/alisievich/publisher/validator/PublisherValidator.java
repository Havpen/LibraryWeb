package com.alisievich.publisher.validator;

import com.alisievich.exception.GenericErrorException;
import com.alisievich.publisher.model.Publisher;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PublisherValidator {
    public void validate(Publisher publisher){
        String regex = "^[а-яА-ЯёЁ\\s]+$";
        if(publisher.getName() == null || !Pattern.matches(regex, publisher.getName())){
            throw new GenericErrorException("Поле должно содержать только буквы русского алфавита и пробелы");
        }
    }
}
