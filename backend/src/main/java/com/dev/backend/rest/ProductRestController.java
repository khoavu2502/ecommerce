package com.dev.backend.rest;

import com.dev.backend.dto.ProductDTO;
import com.dev.backend.entity.Product;
import com.dev.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class ProductRestController {

    private final ProductService productService;

    @PostMapping("/products")
    public ProductDTO addProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("/products/{id}")
    public Optional<ProductDTO> getProduct(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/products")
    public List<ProductDTO> getProducts() {
        return productService.findAll();
    }

    @GetMapping("/products/search")
    public List<ProductDTO> getProductsByCategoryId(@RequestParam Long categoryId) {
        return productService.findByCategoryId(categoryId);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
