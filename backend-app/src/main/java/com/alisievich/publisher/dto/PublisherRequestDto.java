package com.alisievich.publisher.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PublisherRequestDto {
    private Integer id;
    private String name;
    private String address;
}
