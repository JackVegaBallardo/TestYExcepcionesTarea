package com.nttdata.pedido.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PedidoCreateRequest {
    @NotNull
    private Long userId;
    private LocalDate fechaPedido;
    private String estado;
    @NotEmpty
    private List<DetalleCreateRequest> detalles;
}
