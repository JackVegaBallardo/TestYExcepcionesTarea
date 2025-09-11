package com.nttdata.pedido.Model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_pedido", nullable = false)
    private LocalDate fechaPedido;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoPedido estado;

    // Usuario externo ==(otro microservicio)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "total", precision = 19, scale = 2, nullable = false)
    private BigDecimal total = BigDecimal.ZERO;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detalles = new ArrayList<>();


    public void addDetalle(DetallePedido d) {
        d.setPedido(this);
        detalles.add(d);
        recalcTotal();
    }
    public void removeDetalle(DetallePedido d) {
        d.setPedido(null);
        detalles.remove(d);
        recalcTotal();
    }
    public void recalcTotal() {
        this.total = detalles.stream()
                .map(DetallePedido::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}