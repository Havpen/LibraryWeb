package com.alisievich.reservation.model;


import com.alisievich.book.book_instance.model.BookInstance;
import com.alisievich.reader.model.Reader;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @NotNull
    @Column(name = "reservation_date")
    private LocalDate reservationDate;

    @NotNull
    @Column(name = "reservation_deadline")
    private LocalDate reservationDeadLine;

    @NotNull
    @Column(name = "status")
    private String status;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instance_id")
    private BookInstance bookInstance;
}
