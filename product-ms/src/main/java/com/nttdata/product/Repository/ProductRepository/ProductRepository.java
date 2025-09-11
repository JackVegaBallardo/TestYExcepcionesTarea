package com.nttdata.product.Repository.ProductRepository;

import com.nttdata.product.Model.Entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    Product update(Product product);

    void deleteById(Long id);

    List<Product> findByCategoryName(String categoryName);
}

