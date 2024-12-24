package com.alisievich.javafxapp.reader.model;

import lombok.*;
import java.time.LocalDate;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reader {
    private Integer id;
    private String name;
    private LocalDate birthday;
    private String address;
    private String phone;
    private String email;
    private LocalDate registrationDate;
    private Integer cardNumber;
    @Override
    public String toString() {
        return getName();
    }
}
