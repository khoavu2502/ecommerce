package com.dev.backend.service.impl;

import com.dev.backend.dto.CategoryDTO;
import com.dev.backend.dto.ProductDTO;
import com.dev.backend.entity.Category;
import com.dev.backend.exception.ResourceNotFoundException;
import com.dev.backend.repository.CategoryRepository;
import com.dev.backend.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public CategoryDTO save(Category category) {
        Category dbCategory = categoryRepository.save(category);
        return modelMapper.map(dbCategory, CategoryDTO.class);
    }

    @Override
    public Optional<CategoryDTO> findById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            return Optional.ofNullable(modelMapper.map(optionalCategory.get(), CategoryDTO.class));
        }

        else throw new ResourceNotFoundException("Cannot find product with id: " + id);
    }

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> productCategories = categoryRepository.findAll();
        return productCategories.stream()
                .map(productCategory -> modelMapper.map(productCategory, CategoryDTO.class))
                .toList();
    }

    @Override
    public List<ProductDTO> findProductsById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            return optionalCategory.get().getProducts()
                    .stream()
                    .map(product -> modelMapper.map(product, ProductDTO.class))
                    .toList();
        }
        else throw new ResourceNotFoundException("Cannot find product category with id: " + id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) categoryRepository.deleteById(id);
        else throw new ResourceNotFoundException("Cannot find product category with id: " + id);
    }
}
