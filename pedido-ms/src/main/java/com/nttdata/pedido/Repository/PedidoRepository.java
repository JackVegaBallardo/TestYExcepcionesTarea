package com.nttdata.pedido.Repository;

import com.nttdata.pedido.Model.Entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
