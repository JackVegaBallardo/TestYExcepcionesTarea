package com.nttdata.pedido.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PedidoResponse {
    private Long id;
    private LocalDate fechaPedido;
    private String estado;
    private Long userId;
    private BigDecimal total;
    private List<DetalleResponse> detalles;
}