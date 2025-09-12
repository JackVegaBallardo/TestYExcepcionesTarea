package com.nttdata.compositepedido.Controller;

import com.nttdata.compositepedido.DTO.PedidoCreateRequest;
import com.nttdata.compositepedido.DTO.PedidoResponse;
import com.nttdata.compositepedido.Service.CompositePedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/composite/pedidos")
public class CompositePedidoController {

    private final CompositePedidoService service;

    @PostMapping
    public ResponseEntity<PedidoResponse> crear(@Valid @RequestBody PedidoCreateRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crearPedido(req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }
}