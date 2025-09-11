package com.nttdata.pedido.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
public class DetalleResponse {
    private Long id;
    private Long productId;
    private String productName;
    private BigDecimal precioUnitario;
    private Integer cantidad;
    private BigDecimal subtotal;
}