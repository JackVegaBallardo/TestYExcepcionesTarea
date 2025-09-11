package com.nttdata.pedido.Service;

import com.nttdata.pedido.Model.Entity.DetallePedido;
import com.nttdata.pedido.Model.Entity.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoService {
    Pedido crear(Pedido pedido, List<DetallePedido> detalles);
    void eliminar(Long id);
}