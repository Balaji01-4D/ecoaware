package com.ecoaware.tracker.repo;

import com.ecoaware.tracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
