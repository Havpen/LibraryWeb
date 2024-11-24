package com.alisievich.book.service;

import com.alisievich.book.dto.BookDto;
import com.alisievich.book.mapper.BookDtoMapper;
import com.alisievich.book.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service//Указывает, что этот класс является сервисом, то есть компонентом уровня бизнес-логики
@AllArgsConstructor//
@Transactional//Аннотация, указывающая, что все методы в этом классе должны выполняться в рамках транзакции. Если в процессе выполнения возникнет ошибка, транзакция будет откатана.
public class BookService {//
    private final BookRepository bookRepository;//Поля final так как должны быть инициализированы через конструктор или сразу при объявлении.
    private final BookDtoMapper bookDtoMapper;//

    public List<BookDto> getAll(){//Метод, возвращающий список всех книг в виде DTO
        return IterableUtils.toList(bookRepository.findAll()).stream().map(bookDtoMapper::map)//Преобразует Iterable, возвращаемый findAll(), в List.
                .collect(Collectors.toList());//создаёт поток Stream, где каждый объект Book преобразуется в BookDto с помощью метода map из BookDtoMapper, а затем собирается обратно в List
    }
}
