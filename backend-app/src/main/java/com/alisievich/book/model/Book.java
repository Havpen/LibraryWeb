package com.alisievich.book.model;

import com.alisievich.genre.model.Genre;
import com.alisievich.publisher.model.Publisher;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity//указывает на то, что данные класс является сущностью, которая будет отображаться в базе данных в таблице
@Table(name = "books")//указывает на то, что в базе данных таблица с этой сущностью будет называться books, если ты бы этой строчки не было, таблица называлась бы именем класса
@Getter//автоматическое создания геттера
@Setter//автоматическое создание сеттера
@Builder//автоматическое создание "строителя" для создания экземпляров класса
@NoArgsConstructor//автоматическое создание конструктора без аргументов для всех полей
@AllArgsConstructor//автоматическое создание конструктора с аргументами для всех полей
@EntityListeners(AuditingEntityListener.class)//добавляет слушателя, который помогает отслеживать изменение сущности (например время добавления и обновления)
public class Book {//создание класса Book
    @Id//аннотация указывает на то, что поле ID является первичным ключом таблицы
    @GeneratedValue(strategy = GenerationType.IDENTITY)//настраивает автоматическую генерацию значения ID с использованием технологии
    private Integer id;

    @NotNull//значение title не может быть пустым
    @Column(name = "title")//данные будут храниться в таблице в колонке title
    private String title;

    @NotNull//значение year не может быть пустым
    @Column(name = "year")//данные будут храниться в таблице в колонке year
    private Integer year;

    @NotNull//значение language не может быть пустым
    @Column(name = "language")//данные будут храниться в таблице в колонке language
    private String language;

    @NotNull//значение publisher не может быть пустым
    @ManyToOne(fetch = FetchType.LAZY)// Указывает связь "многие к одному" с сущностью Publisher. LAZY обозначает что publisher будет загружен только по необходимости
    @JoinColumn(name = "publisher_id")//Указывает на внешний ключ published_id, связывающий эту сущность с таблицей Publisher
    private Publisher publisher;

    @NotNull//значение genre не может быть пустым
    @ManyToOne(fetch = FetchType.LAZY)//Указывает связь "многие к одному" с сущностью Genre. LAZY обозначает, что genre будет загружен только по необходимости
    @JoinColumn(name = "genre_id")//Указывает на внешний ключ genre_id, связывающий эту сущность с таблицей Genre
    private Genre genre;

    @Builder.Default
    @OneToMany(
            mappedBy = "book",
            cascade = CascadeType.ALL,//означает, что все операции (сохранение, удаление и т.д.) будут каскадировать на связанных авторов
            orphanRemoval = true//удаляет "сирот" из базы данных, если они больше не связаны с книгой
    )//настройка связи один ко многим с BookAuthor.
    private List<BookAuthor> authors = new ArrayList<>();//это список авторов книг, связанный через BookAuthor
}
//Builder - используется для создания объектов с помощью поэтапной настройки. Он полезен, когда объект имеет много полей, и не все из них обязательно нужно указывать при создании экземпляра
// автоматически генерируется класс Builder для сущностиЮ что помогает создавать объект следующим образом:
// Book book = Book.builder()
//        .title("Some Book Title")
//        .year(2023)
//        .language("English")
//        .build();
//Java Persistence API (JPA) - стандарт Java для работы с базами данных. JPA предоставляет аннотации и интерфейсы для удобного отображения объектов Java на таблицы базы данных, управления транзакциями, написания запросов и выполнения других операций с данными.
//Пример аннотации:
//@Entity - указывает, что класс является сущностью.
//@ID - указывает первичный ключ.
//@ManyToOne @OneToMany - устанавливают связи между сущностями.
//JPA помогает сделать код более понятным и простым для поддержки
//Primary Key - первичный ключ, уникальный идентификатор каждой записи в таблице базы данных, в контексте JPA используется @id для определения поля как первичного ключа.
//Foreign Key - внешний ключ, это поле в таблице, которое создаёт связь между двумя таблицами. Внешний ключ ссылается на первичный ключ другой таблицы. Например, в классе Book поле publisher является внешним ключом, который ссылается на таблицу Publisher
//@ManyToOne(fetch = FetchType.LAZY)
//@JoinColumn(name = "publisher_id")
//private Publisher publisher;
//Здесь published_id в таблице books ссылается на id в таблице Publisher, что позволяет установить связь между книгой и её издателем
//Composite Key - это ключ состоящий из двух или более полей, которые в совокупности должны быть уникальными для каждой строки. В модели BookAuthor используется составной ключ BookAuthorID, состоящий из authorId и bookId:
//@EmbeddedId
//private BookAuthorId id;
//Составной ключ обычно используется для промежуточных таблиц (например, для связи "многие ко многим"), где сочетание нескольких полей необходимо для уникальной идентификации записи.
//equals определяет, равны ли два объекта
//hashCode - возвращает целочисленное значение, представляющее объект. Это значение используется для ускоренного поиска объектов в коллекциях, таких как HashMap, HachSet и HashTable. Если два объекта равны с точки зрения equals, их hashCode тоже должен быть одинаковым
//

