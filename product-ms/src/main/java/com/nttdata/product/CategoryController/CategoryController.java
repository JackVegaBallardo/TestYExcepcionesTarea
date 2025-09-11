package com.nttdata.product.CategoryController;

import com.nttdata.product.Mapper.CategoryMapper;
import com.nttdata.product.Model.DTO.CategoryDTO.CategoryDto;
import com.nttdata.product.Model.DTO.CategoryDTO.CategoryPatchDto;
import com.nttdata.product.Model.DTO.CategoryDTO.CategorySaveRequestDto;
import com.nttdata.product.Model.DTO.CategoryDTO.CategorySaveResponseDto;
import com.nttdata.product.Model.Entity.Category;
import com.nttdata.product.Service.categoryService.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import static com.nttdata.product.Mapper.CategoryMapper.INSTANCE;


@RestController
@RequestMapping("/api/category")
public class CategoryController {

    //@Autowired
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(INSTANCE.map(categoryService.listAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        Category cat = categoryService.findByIdOrThrow(id);
        return ResponseEntity.ok(INSTANCE.map(cat));
    }

    @PostMapping
    public ResponseEntity<CategorySaveResponseDto> save(@Valid @RequestBody CategorySaveRequestDto request) {
        Category saved = categoryService.save(INSTANCE.toEntity(request));
        URI location = URI.create("/api/category/" + saved.getId());
        return ResponseEntity.created(location).body(INSTANCE.toCategorySaveResponseDto(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        Category existing = categoryService.findByIdOrThrow(id);
        categoryService.delete(existing);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDto dto) {
        categoryService.findByIdOrThrow(id);
        Category mapped = CategoryMapper.INSTANCE.map(dto);
        mapped.setId(id);
        return ResponseEntity.ok(categoryService.update(mapped));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Category> patchCategory(@PathVariable Long id, @Valid @RequestBody CategoryPatchDto patchDto) {
        Category existing = categoryService.findByIdOrThrow(id);
        INSTANCE.updateCategoryFromPatchDto(patchDto, existing);
        return ResponseEntity.ok(categoryService.update(existing));
    }
}