package com.nttdata.compositepedido.Feign;


import com.nttdata.compositepedido.DTO.PedidoCreateRequest;
import com.nttdata.compositepedido.DTO.PedidoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "pedidoClient",
        url = "${pedido.ms.url}",
        path = "/pedidos"
)
public interface PedidoClient {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PedidoResponse crear(@RequestBody PedidoCreateRequest req);

    @DeleteMapping("/{id}")
    void eliminar(@PathVariable("id") Long id);
}