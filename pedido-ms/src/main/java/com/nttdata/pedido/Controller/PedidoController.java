package com.nttdata.pedido.Controller;

import com.nttdata.pedido.DTO.DetalleResponse;
import com.nttdata.pedido.DTO.PedidoCreateRequest;
import com.nttdata.pedido.DTO.PedidoResponse;
import com.nttdata.pedido.Model.Entity.DetallePedido;
import com.nttdata.pedido.Model.Entity.EstadoPedido;
import com.nttdata.pedido.Model.Entity.Pedido;
import com.nttdata.pedido.Service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponse> crear(@Valid @RequestBody PedidoCreateRequest req) {

        Pedido pedido = new Pedido();
        pedido.setUserId(req.getUserId());
        pedido.setFechaPedido(
                req.getFechaPedido() != null ? req.getFechaPedido() : LocalDate.now()
        );
        pedido.setEstado(
                req.getEstado() != null ? EstadoPedido.valueOf(req.getEstado()) : EstadoPedido.PENDIENTE
        );


        List<DetallePedido> detalles = req.getDetalles().stream().map(d -> {
            DetallePedido det = new DetallePedido();
            det.setProductId(d.getProductId());
            det.setProductName(d.getProductName());
            det.setPrecioUnitario(d.getPrecioUnitario());
            det.setCantidad(d.getCantidad());
            det.calcSubtotal();
            return det;
        }).toList();

        Pedido creado = pedidoService.crear(pedido, detalles);


        PedidoResponse res = new PedidoResponse(
                creado.getId(),
                creado.getFechaPedido(),
                creado.getEstado().name(),
                creado.getUserId(),
                creado.getTotal(),
                creado.getDetalles().stream()
                        .map(dd -> new DetalleResponse(
                                dd.getId(),
                                dd.getProductId(),
                                dd.getProductName(),
                                dd.getPrecioUnitario(),
                                dd.getCantidad(),
                                dd.getSubtotal()
                        )).toList()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pedidoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
