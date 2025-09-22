package com.RegistrationSystemWithSpringSecurity.RegistrationSystemWithSpringSecurity.controller;

import com.RegistrationSystemWithSpringSecurity.RegistrationSystemWithSpringSecurity.model.Category;
import com.RegistrationSystemWithSpringSecurity.RegistrationSystemWithSpringSecurity.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllProducts();
    }

    @GetMapping("/category/{categoryId}")
    public Category getCategory(@PathVariable Long categoryId) {
        return categoryService.getCategory(categoryId);
    }

    @PostMapping("/add")
    public Category addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @DeleteMapping("/remove/{categoryId}")
    public void removeCategory(@PathVariable Long categoryId) {
        categoryService.removeCategory(categoryId);
    }

    @PutMapping("/update/{categoryId}")
    public Category updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
        return categoryService.updateCategory(categoryId, category);
    }
}
