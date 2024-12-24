package com.alisievich.javafxapp.reservation.mapper;

import com.alisievich.javafxapp.reader.mapper.ReaderMapper;
import com.alisievich.javafxapp.reservation.dto.ReservationResponseDto;
import com.alisievich.javafxapp.reservation.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = ReaderMapper.class)
public interface ReservationMapper {
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);
    Reservation reservationResponseDtoToReservation(ReservationResponseDto responseDto);
}
