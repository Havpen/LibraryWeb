package com.alisievich.reservation.service;

import com.alisievich.book.book_instance.model.BookInstance;
import com.alisievich.book.book_instance.repository.BookInstanceRepository;
import com.alisievich.book.model.Book;
import com.alisievich.book.repository.BookRepository;
import com.alisievich.common.service.CrudService;
import com.alisievich.reader.model.Reader;
import com.alisievich.reader.repository.ReaderRepository;
import com.alisievich.reservation.dto.ReservationRequestDto;
import com.alisievich.reservation.model.Reservation;
import com.alisievich.reservation.repository.ReservationRepository;
import com.alisievich.reservation.validator.ReservationValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ReservationService extends CrudService<Reservation, Integer> {
    private final ReservationRepository reservationRepository;
    private final BookInstanceRepository bookInstanceRepository;
    private final ReservationValidator reservationValidator;
    private final ReaderRepository readerRepository;
    private final BookRepository bookRepository;

    public ReservationService(ReaderRepository readerRepository,
                              ReservationRepository reservationRepository,
                              BookInstanceRepository bookInstanceRepository,
                              ReservationValidator reservationValidator,
                              BookRepository bookRepository){
        super(reservationRepository);
        this.reservationRepository = reservationRepository;
        this.bookInstanceRepository = bookInstanceRepository;
        this.reservationValidator = reservationValidator;
        this.readerRepository = readerRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public Reservation create(ReservationRequestDto requestDto){
        BookInstance bookInstance = bookInstanceRepository.findByBarcode(requestDto.getBookInstance().getBarcode()).orElse(null);

        if (bookInstance == null) {
            Book book = bookRepository.findById(requestDto.getBookInstance().getBook().getId()).orElseThrow(EntityNotFoundException::new);
            bookInstance = BookInstance.builder()
                    .barcode(requestDto.getBookInstance().getBarcode())
                    .book(book)
                    .build();
            bookInstanceRepository.save(bookInstance);
        }

        Reader reader = readerRepository.findById(requestDto.getReader().getId()).orElseThrow(EntityNotFoundException::new);
        Reservation reservation = Reservation.builder()
                .reader(reader)
                .reservationDate(requestDto.getReservationDate())
                .reservationDeadLine(requestDto.getReservationDeadLine())
                .status(requestDto.getStatus())
                .bookInstance(bookInstance)
                .build();

        reservationValidator.validate(reservation);
        return reservationRepository.save(reservation);
    }

    public Reservation update(Integer id, ReservationRequestDto requestDto){
        Reservation reservation = reservationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        reservation.setReservationDate(requestDto.getReservationDate());
        reservation.setReservationDeadLine(requestDto.getReservationDeadLine());
        reservation.setStatus(requestDto.getStatus());
        reservationValidator.validate(reservation);
        return reservationRepository.save(reservation);
    }
}
