package com.dev.backend.rest;

import com.dev.backend.dto.CategoryDTO;
import com.dev.backend.dto.ProductDTO;
import com.dev.backend.entity.Category;
import com.dev.backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryRestController {

    private final CategoryService categoryService;

    @PostMapping("/categories")
    public CategoryDTO addCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @GetMapping("/categories/{id}")
    public Optional<CategoryDTO> getCategory(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @GetMapping("/categories")
    public List<CategoryDTO> getCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/categories/{id}/products")
    public List<ProductDTO> getProductsById(@PathVariable Long id) {
        return categoryService.findProductsById(id);
    }

    @PatchMapping("/categories")
    public CategoryDTO updateCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
