package com.nttdata.stock.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ErrorDetailDto {
    private String message;
    private String dateTime;
}