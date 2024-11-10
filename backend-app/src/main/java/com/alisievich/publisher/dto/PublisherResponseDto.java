package com.alisievich.publisher.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublisherResponseDto {
    private Integer id;
    private String name;
    private String address;
}
