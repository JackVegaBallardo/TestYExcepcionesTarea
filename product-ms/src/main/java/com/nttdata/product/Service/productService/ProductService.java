package com.nttdata.product.Service.productService;


import com.nttdata.product.Model.Entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    Product update(Product product);
    void deleteById(Long id);
    List<Product> findByCategoryName(String categoryName);
}
