package com.alisievich.book.model;

import com.alisievich.author.model.Author;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Entity //Аннотация указывает на то, что данный класс является сущностью и будет отображаться в базе данных
@Table(name = "book_authors") //Указывает что данная сущность будет храниться в таблице BookAuthor, если бы это аннотация отсутствовала, то имя таблицы было бы такое же, как и название класса
@Getter //метод получения
@Setter //метод задавания =)
@Builder//Аннотация Lombok, позволяющая использовать паттерн "Строитель" (Builder) для создания экземпляров класса
@NoArgsConstructor//автоматически создаёт конструктор без аргументов для всех полей
@AllArgsConstructor//автоматически создаёт конструктор с аргументами для всех полей
@EntityListeners(AuditingEntityListener.class)//Подключает слушатель, который помогает автоматически отслеживать изменения сущности (например, время создания и обновления)
public class BookAuthor {
    @EmbeddedId//Используется для создания составного ключа для bookID и authorID. Составной ключ создается с помощью внутреннего класса BookAuthorID
    private BookAuthorId id;

    @ManyToOne(fetch = FetchType.LAZY)//Указывает, что BookAuthor связан с Book через один ко многим, и загрузка book происходит только при обращении к нему
    @MapsId("bookId")//Говорит JPA, что bookId в BookAuthorID должен быть использован как внешний ключ для этой связи
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)//Указывает, что BookAuthor связан с Author через один ко многим, и загрузка book происходит только при обращении к нему
    @MapsId("authorId")//Говорит JPA, что authorId в BookAuthorID должен быть использован как внешний ключ для этой связи
    private Author author;

    @Embeddable//Указывает, что класс можно встраивать в другие классы как составной ключ
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode//Генерирует методы equals и hashCode, что важно для корректного сравнения объектов BookAuthorID
    public static class BookAuthorId implements Serializable {
        @NotNull//Указывает на то что значение не может быть пустым
        @Column(name = "author_id")//указывает, что данные должны лежать в определенной колонке в базе данных
        private Integer authorId;

        @NotNull//идентично author_id
        @Column(name = "book_id")
        private Integer bookId;
    }
}
