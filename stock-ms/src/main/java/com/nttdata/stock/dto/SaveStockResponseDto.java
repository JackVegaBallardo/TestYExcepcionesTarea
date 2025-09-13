package com.nttdata.stock.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class SaveStockResponseDto {

    private Integer id;
    private Integer productId;

    @JsonProperty("wareHouseId")
    private Integer warehouseId;

    private Integer quantity;
}