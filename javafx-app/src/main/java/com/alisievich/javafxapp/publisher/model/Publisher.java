package com.alisievich.javafxapp.publisher.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Publisher {
    private Integer id;
    private String name;
    private String address;
    @Override
    public String toString() {
        return getName();
    }
}
