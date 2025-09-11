package com.nttdata.compositepedido.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class DetalleResponse {
    private Long id;
    private Long productId;
    private String productName;
    private Double precioUnitario;
    private Integer cantidad;
    private Double subtotal;
}