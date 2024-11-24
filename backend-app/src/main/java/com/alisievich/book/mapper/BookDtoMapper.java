package com.alisievich.book.mapper;

import com.alisievich.book.dto.BookDto;//импортирует BookDto
import com.alisievich.book.model.Book;//импортирует сущность Book
import org.mapstruct.Mapper;//Импортирует аннотацию Mapper из библиотеки MapStruct, которая сообщает MapStruct, что этот класс будет использоваться для преобразования объектов

@Mapper//Аннотация MapStruct, которая указывает, что этот класс - маппер, который будет использоваться для переобразования объектов между собой. MapStruct автоматически сгенерирует код для абстрактных методов этого класса
public abstract class BookDtoMapper {//Класс объявлен абстрактным, потому что он содержит абстрактным методы, которые MapStruct должен реализовать автоматически
    public BookDto map (Integer id){//Метод, который принимает Integer id и возвращает объект BookDto. В этом методе используется паттерн Builder для создания объекта BookDto с заданным идентификатором id
        return BookDto.builder().id(id).build();//Это использования Builder Pattern для создания объект BookDto. В данном случае, объект BookDto создается с заданным полем id
    }
    public abstract BookDto map (Book book);//Абстрактный метод, который должен быть реализован MapStruct. Этот метод принимает объект типа Book и должен вернуть объект BookDto. MapStruct автоматически сгенерирует реализацию этого метода на основе полей класса Book и BookDto
}
