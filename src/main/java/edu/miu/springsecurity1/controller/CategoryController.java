package edu.miu.springsecurity1.controller;


import edu.miu.springsecurity1.entity.Review;
import edu.miu.springsecurity1.entity.dto.request.CategoryDtoRequest;
import edu.miu.springsecurity1.entity.dto.response.CategoryDtoResponse;
import edu.miu.springsecurity1.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void save(@RequestBody CategoryDtoRequest p) {
        categoryService.save(p);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<CategoryDtoResponse> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDtoResponse> getById(@PathVariable int id) {
        var category = categoryService.getById(id);
        return ResponseEntity.ok(modelMapper.map(category, CategoryDtoResponse.class));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable int id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@PathVariable("id") int categoryId) {
        //call service
    }
}
