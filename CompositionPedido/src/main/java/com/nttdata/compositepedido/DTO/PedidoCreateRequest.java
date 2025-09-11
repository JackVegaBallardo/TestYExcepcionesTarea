package com.nttdata.compositepedido.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PedidoCreateRequest {
    @NotNull
    private Long userId;
    private LocalDate fechaPedido;
    private String estado;

    @NotEmpty
    private List<DetalleCreateRequest> detalles;
}
