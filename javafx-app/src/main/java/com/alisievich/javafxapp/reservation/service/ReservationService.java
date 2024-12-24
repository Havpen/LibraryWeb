package com.alisievich.javafxapp.reservation.service;

import com.alisievich.javafxapp.client.BackendClient;
import com.alisievich.javafxapp.reservation.dto.ReservationRequestDto;
import com.alisievich.javafxapp.reservation.dto.ReservationResponseDto;
import com.alisievich.javafxapp.reservation.mapper.ReservationMapper;
import com.alisievich.javafxapp.reservation.model.Reservation;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ReservationService {
    private BackendClient client;
    public ReservationService(){
        client = BackendClient.getInstance();
    }
    public CompletableFuture<List<Reservation>> getAllReservations(){
        ReservationMapper reservationMapper = ReservationMapper.INSTANCE;
        CompletableFuture<ReservationResponseDto[]> reservationDtoFuture = client.apiRequest("reservations", ReservationResponseDto[].class);
        return reservationDtoFuture.thenApply(reservations -> {
            return Arrays.stream(reservations).map(reservationMapper::reservationResponseDtoToReservation).toList();
        });
    }
    public CompletableFuture<Reservation> getReservationById(Integer reservationId){
        ReservationMapper reservationMapper = ReservationMapper.INSTANCE;
        CompletableFuture<ReservationResponseDto> reservationDtoFuture = client.apiRequest("reservations/" + reservationId, ReservationResponseDto.class);
        return reservationDtoFuture.thenApply(reservationMapper::reservationResponseDtoToReservation);
    }
    public CompletableFuture<Reservation> createReservation(ReservationRequestDto reservationRequestDto){
        ReservationMapper reservationMapper = ReservationMapper.INSTANCE;
        CompletableFuture<ReservationResponseDto> reservationCreateDtoFuture = client.create("reservations", reservationRequestDto, ReservationResponseDto.class);
        return reservationCreateDtoFuture
                .handle((result, ex) -> {
                    if (ex != null) {
                        throw new RuntimeException(ex);
                    }
                    return reservationMapper.reservationResponseDtoToReservation(result);
                });
    }
    public CompletableFuture<Reservation> updateReservation(Integer reservationId, ReservationRequestDto reservationRequestDto){
        ReservationMapper reservationMapper = ReservationMapper.INSTANCE;
        CompletableFuture<ReservationResponseDto> reservationUpdateDtoFuture = client.update("reservations/" + reservationId, reservationRequestDto, ReservationResponseDto.class);
        return reservationUpdateDtoFuture.thenApply(reservationMapper::reservationResponseDtoToReservation);
    }
    public CompletableFuture<Void> deleteReservation(Integer reservationId){
        return client.delete("reservations/" + reservationId);
    }
}
