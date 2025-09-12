package com.nttdata.compositepedido.Service;

import com.nttdata.compositepedido.DTO.PedidoCreateRequest;
import com.nttdata.compositepedido.DTO.PedidoResponse;

public interface CompositePedidoService {
    PedidoResponse crearPedido(PedidoCreateRequest req);
    void eliminarPedido(Long id);
}