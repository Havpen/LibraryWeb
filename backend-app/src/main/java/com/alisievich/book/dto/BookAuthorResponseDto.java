package com.alisievich.book.dto;

import com.alisievich.author.dto.AuthorResponseDto;
import lombok.*;

@Getter//автоматический геттер
@Setter//автоматический сеттер
@AllArgsConstructor//конструктор по умолчанию с аргументами для всех полей
@NoArgsConstructor//конструктор без аргументов для всех полей
@Builder//строитель для простого создания объектов
public class BookAuthorResponseDto {
    BookIdDto book;//ссылка на DTO с информацией о книге, но только с идентификатором.
    AuthorResponseDto author;//ссылка на DTO содержащий информацию об авторе.
}
