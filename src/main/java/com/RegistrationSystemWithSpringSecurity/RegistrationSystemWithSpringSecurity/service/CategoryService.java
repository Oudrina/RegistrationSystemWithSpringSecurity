package com.RegistrationSystemWithSpringSecurity.RegistrationSystemWithSpringSecurity.service;

import com.RegistrationSystemWithSpringSecurity.RegistrationSystemWithSpringSecurity.model.Category;
import com.RegistrationSystemWithSpringSecurity.RegistrationSystemWithSpringSecurity.model.Product;
import com.RegistrationSystemWithSpringSecurity.RegistrationSystemWithSpringSecurity.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllProducts() {
        return categoryRepository.findAll();
    }

    public Category getCategory(Long categoryId) {
        return categoryRepository
                .findById(categoryId).
                orElseThrow(RuntimeException::new);

    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void removeCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public Category updateCategory(Long categoryId, Category updatecategory) {
        Category existingCategory = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category ID not found" + categoryId));

        existingCategory.setName(updatecategory.getName());

        return categoryRepository.save(existingCategory);
    }


}
