package com.nttdata.compositepedido.Service;

import com.nttdata.compositepedido.DTO.PedidoCreateRequest;
import com.nttdata.compositepedido.DTO.PedidoResponse;
import com.nttdata.compositepedido.Feign.PedidoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompositePedidoServiceImpl implements CompositePedidoService {

    private final PedidoClient pedidoClient;

    @Override
    public PedidoResponse crearPedido(PedidoCreateRequest req) {
        return pedidoClient.crear(req);
    }

    @Override
    public void eliminarPedido(Long id) {
        pedidoClient.eliminar(id);
    }
}