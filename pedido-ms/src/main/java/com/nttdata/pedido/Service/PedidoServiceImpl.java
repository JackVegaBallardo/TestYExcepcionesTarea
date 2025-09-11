package com.nttdata.pedido.Service;

import com.nttdata.pedido.Model.Entity.DetallePedido;
import com.nttdata.pedido.Model.Entity.Pedido;
import com.nttdata.pedido.NotFoundException;
import com.nttdata.pedido.Repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    @Override
    public Pedido crear(Pedido pedido, List<DetallePedido> detalles) {
        if (pedido.getUserId() == null) {
            throw new IllegalArgumentException("userId es obligatorio");
        }
        pedido.setDetalles(new ArrayList<>());
        if (detalles != null) {
            for (DetallePedido d : detalles) {
                pedido.addDetalle(d);
            }
        }
        pedido.setFechaPedido(
                Objects.requireNonNullElse(pedido.getFechaPedido(), LocalDate.now())
        );
        return pedidoRepository.save(pedido);
    }

    @Override
    public void eliminar(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new NotFoundException("Pedido con id " + id + " no existe");
        }
        pedidoRepository.deleteById(id);
    }
}
