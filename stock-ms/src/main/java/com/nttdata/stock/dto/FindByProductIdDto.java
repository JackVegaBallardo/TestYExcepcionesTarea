package com.nttdata.stock.dto;


import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class FindByProductIdDto {
    private Integer productId;
    private Integer total;
}