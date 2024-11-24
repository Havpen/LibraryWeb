package com.alisievich.book.controller;

import com.alisievich.book.dto.BookDto;
import com.alisievich.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController//Аннотация указывающая, что данный класс является контроллером Spring REST. Он включает в себя @Controller и @ResponseBody, что позволяет каждому методу автоматически возвращать данные в формате JSON или любом, подходящем для REST API
@RequestMapping(value = "/api/v1/books")//Базовый URL для всех маршрутов, доступных в этом контроллере. В данном случае, методы контроллера будут доступны по пути /api/v1/books. Например, вызов метода getAll() будет доступен по пути /api/v1/books
@AllArgsConstructor//Конструктор по умолчанию с аргументами для всех полей
public class BookController {
    private final BookService bookService;//Это сервис, который инжектируется в контроллер через конструктор, сгенерированный @AllArgsConstructor. Он используется для выполнения бизнес-логики, такой как получения данных о книгах. private final делает поле неизменяем, что является хорошей практикой для инъекций зависимостей

    @Operation(summary = "Get all books")//Это аннотация из Swagger/OpenAI используется для документирования REST API. Она указывает, что метод getAll предоставляет возможность "получить все книги". При подключении Swagger к проекту, эта аннотация добавит описания метода в автоматически сгенерированную документацию API
    @GetMapping//Указывает, что метод getAll будет обрабатываться по HTTP GET запросу. Это стандартный метод для получения данных API. Без дополнительного URL внутри @GetMapping, метод будет доступен по основному пути контроллера, то есть /api/v1/books
    public ResponseEntity<List<BookDto>> getAll(){//Это обертка, используемая для возврата HTTP-ответа, содержащего данные HTTP-статус. Здесь возвращается List<BookDto>, то есть список всех кинг в формате BookDto
        //bookService.getAll() вызывает метод getAll() в сервисе BookService, который должен возвращать список объектов BookDto
        return ResponseEntity.ok(bookService.getAll());//ResponseEntity.ok() создает ответ с HTTP-статусом 200(OK) и передает в теле ответа данные, полученные из bookService.getAll()
    }//
}
