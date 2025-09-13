package com.nttdata.stock.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "stocks",
        indexes = {
                @Index(name = "idx_stock_product", columnList = "product_id"),
                @Index(name = "idx_stock_warehouse", columnList = "warehouse_id")
        },
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_stock_product_warehouse",
                        columnNames = {"product_id", "warehouse_id"}
                )
        }
)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Column(name = "warehouse_id", nullable = false)
    private Integer warehouseId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
