package com.alisievich.javafxapp.reader.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReaderRequestDto {
    private Integer id;
    private String name;
    private LocalDate birthday;
    private String address;
    private String phone;
    private String email;
    private LocalDate registrationDate;
    private Integer cardNumber;
}
