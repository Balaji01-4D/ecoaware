package com.ecoaware.tracker.category;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("invalid category"));
    }

    public CategoryResponse convertToCategoryDto(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setName(category.getName());
        response.setId(category.getId());
        return response;
    }
}
