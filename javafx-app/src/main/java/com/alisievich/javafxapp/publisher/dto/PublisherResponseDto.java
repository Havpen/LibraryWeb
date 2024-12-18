package com.alisievich.javafxapp.publisher.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PublisherResponseDto {
    private Integer id;
    private String name;
    private String address;
}
