package com.nttdata.stock.repository;


import com.nttdata.stock.Entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StockRepository extends JpaRepository<Stock, Integer> {

    @Query("select coalesce(sum(s.quantity), 0) from Stock s where s.productId = :productId")
    Integer sumQuantityByProductId(Integer productId);

    boolean existsByProductId(Integer productId);

    boolean existsByProductIdAndWarehouseId(Integer productId, Integer warehouseId);
}