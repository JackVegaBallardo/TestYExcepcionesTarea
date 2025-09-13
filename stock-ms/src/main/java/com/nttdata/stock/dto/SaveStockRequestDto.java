package com.nttdata.stock.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class SaveStockRequestDto {

    @NotNull(message = "productId es obligatorio")
    private Integer productId;

    @NotNull(message = "wareHouseId es obligatorio")
    @JsonProperty("wareHouseId")
    private Integer warehouseId;

    @NotNull(message = "quantity es obligatorio")
    @Min(value = 0, message = "quantity no puede ser negativo")
    private Integer quantity;
}
