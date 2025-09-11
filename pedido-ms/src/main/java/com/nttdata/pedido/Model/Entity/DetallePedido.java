package com.nttdata.pedido.Model.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    // (otro microservicio): guardamos referencia
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_name", nullable = false, length = 200)
    private String productName;

    @Column(name = "precio_unitario", precision = 19, scale = 2, nullable = false)
    private BigDecimal precioUnitario;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal subtotal;

    @PrePersist @PreUpdate
    public void calcSubtotal() {
        if (precioUnitario != null && cantidad != null) {
            this.subtotal = precioUnitario.multiply(BigDecimal.valueOf(cantidad));
        }
    }
}