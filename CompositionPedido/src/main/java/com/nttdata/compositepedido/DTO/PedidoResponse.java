package com.nttdata.compositepedido.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class PedidoResponse {
    private Long id;
    private LocalDate fechaPedido;
    private String estado;
    private Long userId;
    private Double total;
    private List<DetalleResponse> detalles;
}