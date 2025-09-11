package com.nttdata.pedido.Controller;

import com.nttdata.pedido.DTO.DetalleCreateRequest;
import com.nttdata.pedido.DTO.DetalleResponse;
import com.nttdata.pedido.Model.Entity.DetallePedido;
import com.nttdata.pedido.Model.Entity.Pedido;
import com.nttdata.pedido.NotFoundException;
import com.nttdata.pedido.Repository.PedidoRepository;
import com.nttdata.pedido.Service.DetallePedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class DetallePedidoController {

    private final DetallePedidoService detalleService;
    private final PedidoRepository pedidoRepository;

    @PostMapping("/pedidos/{pedidoId}/detalles")
    public ResponseEntity<DetalleResponse> agregar(
            @PathVariable Long pedidoId,
            @Valid @RequestBody DetalleCreateRequest req) {


        Pedido pedidoRef = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new NotFoundException("Pedido " + pedidoId + " no existe"));

        DetallePedido det = new DetallePedido();
        det.setPedido(pedidoRef);
        det.setProductId(req.getProductId());
        det.setProductName(req.getProductName());
        det.setPrecioUnitario(req.getPrecioUnitario());
        det.setCantidad(req.getCantidad());
        det.calcSubtotal();

        DetallePedido guardado = detalleService.guardar(det);

        DetalleResponse res = new DetalleResponse(
                guardado.getId(),
                guardado.getProductId(),
                guardado.getProductName(),
                guardado.getPrecioUnitario(),
                guardado.getCantidad(),
                guardado.getSubtotal()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @DeleteMapping("/detalles/{detalleId}")
    public ResponseEntity<Void> eliminar(@PathVariable Long detalleId) {
        detalleService.eliminarPorId(detalleId);
        return ResponseEntity.noContent().build();
    }
}
