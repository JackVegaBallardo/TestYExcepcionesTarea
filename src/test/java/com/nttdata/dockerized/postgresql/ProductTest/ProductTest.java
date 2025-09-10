package com.nttdata.dockerized.postgresql.ProductTest;

import com.nttdata.dockerized.postgresql.model.entity.Category;
import com.nttdata.dockerized.postgresql.model.entity.Product;
import com.nttdata.dockerized.postgresql.repository.CategoryRepository.CategoryRepository;
import com.nttdata.dockerized.postgresql.repository.ProductRepository.ProductRepository;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;


    @Test
    void saveDebeLanzarExcepcionSiNombreEsNull() { //porque mi entidad tiene @Column(nullable = false)
        Product p = new Product();
        p.setName(null);
        assertThrows(DataIntegrityViolationException.class, () -> {
            productRepository.save(p);
        });
    }
    @Test
    void seDebeLanzarErrorUsoRecurso() {
        assertThrows(InvalidDataAccessApiUsageException.class,
                () -> productRepository.findByCategoryName(null));
    }
    @Test
    void guardarCategoryProduct(){
        Category c = new Category();
        c.setName("BooksX");
        categoryRepository.save(c);

        Product p = new Product();
        p.setName("Clean Code");
        p.setCategory(c);

        Product saved = productRepository.save(p);

        assertNotNull(saved.getId());
        assertEquals("Clean Code", saved.getName());
        assertEquals("BooksX", saved.getCategory().getName());
    }
    @Test
    void findByCategoryName_devuelveProductosDeLaCategoria() {
        var result = productRepository.findByCategoryName("BooksX");
        assertEquals(1, result.size());
    }
    @Test
    void findAll_devuelveTodosLosProductos() {
        Category books = categoryRepository.save(new Category("Horror"));
        Category games = categoryRepository.save(new Category("Games"));

        productRepository.save(new Product("DDD", books));
        productRepository.save(new Product( "Clean Code", books));
        productRepository.save(new Product( "Zelda", games));

        List<Product> all = productRepository.findAll();

        assertEquals(4, all.size());
    }
    @Test
    void deleteById_eliminaProductoSiExiste() {

        Category books = categoryRepository.save(new Category( "Devices"));
        Product p = productRepository.save(new Product( "RAM", books));
        Long id = p.getId();
        productRepository.deleteById(id);
        Optional<Product> deleted = productRepository.findById(id);
        assertTrue(deleted.isEmpty());
    }
    
}
