package com.dev.backend.service;

import com.dev.backend.dto.CategoryDTO;
import com.dev.backend.dto.ProductDTO;
import com.dev.backend.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    CategoryDTO save(Category category);

    Optional<CategoryDTO> findById(Long id);

    List<CategoryDTO> findAll();

    List<ProductDTO> findProductsById(Long id);

    void deleteById(Long id);
}
