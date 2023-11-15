package com.ea.dataone.controller;

import com.ea.dataone.dto.CategoryDto;
import com.ea.dataone.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public void create(@RequestBody CategoryDto category) {
        categoryService.create(category);
    }

    @GetMapping
    public List<CategoryDto> getAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return categoryService.getCategory(id);
    }

    @PutMapping
    public void update(@RequestBody CategoryDto category) {
        categoryService.update(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        CategoryDto category = categoryService.getCategory(id);
        categoryService.delete(category);
    }
}