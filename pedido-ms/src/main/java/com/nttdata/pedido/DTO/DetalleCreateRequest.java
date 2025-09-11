package com.nttdata.pedido.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DetalleCreateRequest {
    @NotNull
    private Long productId;
    @NotBlank
    private String productName;
    @NotNull @DecimalMin("0.0") private BigDecimal precioUnitario;
    @NotNull @Min(1) private Integer cantidad;
}