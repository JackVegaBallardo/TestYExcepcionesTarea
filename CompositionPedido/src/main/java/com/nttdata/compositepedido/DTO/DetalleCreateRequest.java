package com.nttdata.compositepedido.DTO;



import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DetalleCreateRequest {
    @NotNull
    private Long productId;

    private String productName;

    @Positive
    private Double precioUnitario;

    @Positive
    private Integer cantidad;
}
