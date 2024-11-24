package com.alisievich.reader.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
