package com.alisievich.reservation.service;

import com.alisievich.book.book_instance.model.BookInstance;
import com.alisievich.book.book_instance.repository.BookInstanceRepository;
import com.alisievich.common.service.CrudService;
import com.alisievich.reservation.dto.ReservationRequestDto;
import com.alisievich.reservation.model.Reservation;
import com.alisievich.reservation.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ReservationService extends CrudService<Reservation, Integer> {
    private final ReservationRepository reservationRepository;
    private final BookInstanceRepository bookInstanceRepository;

    public ReservationService(ReservationRepository reservationRepository, BookInstanceRepository bookInstanceRepository){
        super(reservationRepository);
        this.reservationRepository = reservationRepository;
        this.bookInstanceRepository = bookInstanceRepository;
    }

    public Reservation create(ReservationRequestDto requestDto){
        BookInstance bookInstance = bookInstanceRepository.findById(requestDto.getBookInstance().getId()).orElseThrow(EntityNotFoundException::new);
        Reservation reservationModel = Reservation.builder()
                .reservationDate(requestDto.getReservationDate())
                .reservationDeadLine(requestDto.getReservationDeadLine())
                .status(requestDto.getStatus())
                .bookInstance(bookInstance)
                .build();
        return reservationRepository.save(reservationModel);
    }

    public Reservation update(Integer id, ReservationRequestDto requestDto){
        Reservation reservation = reservationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        BookInstance bookInstance = bookInstanceRepository.findById(requestDto.getBookInstance().getId()).orElseThrow(EntityNotFoundException::new);
        reservation.setReservationDate(requestDto.getReservationDate());
        reservation.setReservationDeadLine(requestDto.getReservationDeadLine());
        reservation.setStatus(requestDto.getStatus());
        reservation.setBookInstance(bookInstance);
        return reservationRepository.save(reservation);
    }
}
