package com.alisievich.image.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageRequestDto {
    private Integer id;
    private String filename;
}
