package com.dev.backend.service.impl;

import com.dev.backend.dto.ProductDTO;
import com.dev.backend.entity.Product;
import com.dev.backend.exception.ResourceNotFoundException;
import com.dev.backend.repository.ProductRepository;
import com.dev.backend.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public ProductDTO save(Product product) {
        Product dbProduct = productRepository.save(product);
        return modelMapper.map(dbProduct, ProductDTO.class);
    }

    @Override
    public Optional<ProductDTO> findById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return Optional.ofNullable(modelMapper.map(optionalProduct.get(), ProductDTO.class));
        } else throw new ResourceNotFoundException("Cannot find product with id: " + id);
    }

    @Override
    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
    }

    @Override
    public List<ProductDTO> findByCategoryId(Long id) {
        List<Product> products = productRepository.findByCategoryId(id);
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
    }

    // search product by keyword
    @Override
    public List<ProductDTO> findByName(String name) {
        List<Product> products = productRepository.findByNameContaining(name);
        return products.stream()
                       .map(product -> modelMapper.map(product, ProductDTO.class))
                       .toList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) productRepository.deleteById(id);
        else throw new ResourceNotFoundException("Cannot find product with id: " + id);
    }
}
