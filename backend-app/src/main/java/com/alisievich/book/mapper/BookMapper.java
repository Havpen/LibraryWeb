package com.alisievich.book.mapper;

import com.alisievich.book.model.Book;//Модель, с которой будет работать маппер
import org.mapstruct.Mapper;//Импортирует аннотацию Mapper, необходимо для работы с MapStruct


@Mapper//аннотация, о том, что этот класс будет работать как маппер (преобразовывать объекты между собой)
public abstract class BookMapper {
    public Book map (Integer id){//Метод, который принимает Integer id и возвращает объект book
        return Book.builder()//Создаётся объект Book с использованием id. В данном случае, вызывается билдера для создания объекта с одним полем id
                .id(id)//
                .build();//запуск билдера
    }

    public Integer map (Book book){
        return book.getId();
    }//Метод, который принимает объект типа Book и возвращает его идентификатор id. Здесь просто вызывается метод getId() объекта Book, чтобы извлечь его идентификатор
}
