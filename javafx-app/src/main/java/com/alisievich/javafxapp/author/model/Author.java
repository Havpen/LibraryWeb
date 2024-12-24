package com.alisievich.javafxapp.author.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {
    private Integer id;
    private String name;
    @Override
    public String toString() {
        return getName();
    }
}
