package com.nttdata.product.Service.categoryService;



import com.nttdata.product.Model.Entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> listAll();

    Optional<Category> findById(Long id);

    Category save(Category category);

    void delete(Category category);

    Category update(Category category);

    Category findByIdOrThrow(Long id);

}