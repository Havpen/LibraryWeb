package com.alisievich.reservation.service;

import com.alisievich.common.service.CrudService;
import com.alisievich.reservation.dto.ReservationRequestDto;
import com.alisievich.reservation.model.Reservation;
import com.alisievich.reservation.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ReservationService extends CrudService<Reservation, Integer> {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository){
        super(reservationRepository);
        this.reservationRepository = reservationRepository;
    }

    public Reservation create(ReservationRequestDto requestDto){
        Reservation reservationModel = Reservation.builder().reservationDate(requestDto.getReservationDate()).build();
        return reservationRepository.save(reservationModel);
    }

    public Reservation update(Integer id, ReservationRequestDto requestDto){
        Reservation reservationModel = reservationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        reservationModel.setReservationDate(requestDto.getReservationDate());
        return reservationRepository.save(reservationModel);
    }
}
