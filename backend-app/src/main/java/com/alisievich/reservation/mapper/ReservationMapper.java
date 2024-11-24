package com.alisievich.reservation.mapper;

import com.alisievich.common.service.GenericMapper;
import com.alisievich.reservation.dto.ReservationResponseDto;
import com.alisievich.reservation.model.Reservation;
import org.mapstruct.Mapper;

@Mapper
public interface ReservationMapper extends GenericMapper<Reservation, ReservationResponseDto> {
}
