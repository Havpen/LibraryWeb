package com.alisievich.javafxapp.publisher.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublisherRequestDto {
    private Integer id;
    private String name;
    private String address;
}
