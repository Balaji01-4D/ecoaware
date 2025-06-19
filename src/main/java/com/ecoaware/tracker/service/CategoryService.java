package com.ecoaware.tracker.service;

import com.ecoaware.tracker.DTO.CategoryResponse;
import com.ecoaware.tracker.model.Category;
import com.ecoaware.tracker.repo.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Category findById(Long id) {
        return categoryRepo.findById(id).orElseThrow(() -> new RuntimeException("invalid category"));
    }

    public CategoryResponse convertToCategoryDto(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setName(category.getName());
        return response;
    }
}
