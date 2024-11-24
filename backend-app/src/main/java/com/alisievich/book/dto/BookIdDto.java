package com.alisievich.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter//геттер
@Setter// сеттер =))
@SuperBuilder//расширенная версия builder, которая поддерживает наследование(удобно, если в будущем BookIdDto будет расширяться)
@NoArgsConstructor//конструктор без аргументов
@AllArgsConstructor//конструктов с аргументами
public class BookIdDto {
    private Integer id;//Идентификатор книги. Этот DTO используется, чтобы передавать только идентификатор книги, когда не требуется полная информация о книге
}