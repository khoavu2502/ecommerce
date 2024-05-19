package com.dev.backend.service;

import com.dev.backend.dto.ProductDTO;
import com.dev.backend.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductDTO save(Product product);

    Optional<ProductDTO> findById(Long id);

    List<ProductDTO> findAll();

    List<ProductDTO> findByCategoryId(Long id);

    List<ProductDTO> findByName(String name);

    void deleteById(Long id);
}
