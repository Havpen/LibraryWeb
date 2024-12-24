package com.alisievich.javafxapp.genre.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    private Integer id;
    private String name;
    @Override
    public String toString() {
        return getName();
    }
}
