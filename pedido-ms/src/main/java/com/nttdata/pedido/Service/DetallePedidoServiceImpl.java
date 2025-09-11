package com.nttdata.pedido.Service;

import com.nttdata.pedido.Model.Entity.DetallePedido;
import com.nttdata.pedido.NotFoundException;
import com.nttdata.pedido.Repository.DetallePedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DetallePedidoServiceImpl implements DetallePedidoService {

    private final DetallePedidoRepository detalleRepository;

    @Override
    public DetallePedido guardar(DetallePedido detalle) {
        detalle.calcSubtotal();
        return detalleRepository.save(detalle);
    }

    @Override
    public void eliminarPorId(Long id) {
        if (!detalleRepository.existsById(id)) {
            throw new NotFoundException("DetallePedido con id " + id + " no existe");
        }
        detalleRepository.deleteById(id);
    }
}
