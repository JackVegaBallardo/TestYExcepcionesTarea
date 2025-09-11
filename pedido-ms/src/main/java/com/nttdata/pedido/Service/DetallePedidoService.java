package com.nttdata.pedido.Service;

import com.nttdata.pedido.Model.Entity.DetallePedido;

public interface DetallePedidoService {
    DetallePedido guardar(DetallePedido detalle);
    void eliminarPorId(Long id);
}