package com.alisievich.reservation.controller;

import com.alisievich.reservation.dto.ReservationRequestDto;
import com.alisievich.reservation.dto.ReservationResponseDto;
import com.alisievich.reservation.mapper.ReservationMapper;
import com.alisievich.reservation.model.Reservation;
import com.alisievich.reservation.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/reservations")
@AllArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationMapper responseMapper;

    @Operation(summary = "Get all reservations")
    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> getAll(){
        return ResponseEntity.ok(reservationService.getAll().stream().map(responseMapper::map).toList());
    }

    @Operation(summary = "Get reservation")
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDto> getById(@RequestParam("id") Integer id){
        return ResponseEntity.ok(responseMapper.map(reservationService.getById(id)));
    }

    @Operation(summary = "Add reservation")
    @PostMapping
    public ResponseEntity<ReservationResponseDto> create(@RequestBody ReservationRequestDto reservationRequestDto){
        Reservation reservation = reservationService.create(reservationRequestDto);
        return ResponseEntity.ok(responseMapper.map(reservation));
    }

    @Operation(summary = "Update reservation")
    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponseDto> update(@RequestParam("id") Integer id, @RequestBody ReservationRequestDto reservationRequestDto){
        Reservation reservation = reservationService.update(id, reservationRequestDto);
        return ResponseEntity.ok(responseMapper.map(reservation));
    }

    @Operation(summary = "Delete reservation")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestParam("id") Integer id){
        reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
