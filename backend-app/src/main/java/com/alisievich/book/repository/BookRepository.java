package com.alisievich.book.repository;//Указывает, что BookRepository находится в пакете. Пакеты помогают организовывать код и группировать связанные классы и интерфейсы

import com.alisievich.book.model.Book;//Здесь импортируется модель Book, чтобы репозиторий мог работать с этой сущностью
import org.springframework.data.repository.CrudRepository;//Интерфейс CrudRepository из Spring Data JPA содержит базовые CRUD-операции(Create, Read, Update, Delete), что позволяет управлять сущностями без написания SQL запросов

public interface BookRepository extends CrudRepository<Book, Integer> {//Здесь указывается, что BookRepository будет работать с сущностью Book. Параметры: Book - это тип сущности, с которой работает репозиторий, Integer - это тип идентификатора (первичного ключа) сущности Book (@Id)
}
//Здесь создаётся репозиторий BookRepository, который расширяет CrudRepository. Благодаря этому интерфейсу BookRepository наследует все CRUD-методы, и их можно использовать для работы с Book
//