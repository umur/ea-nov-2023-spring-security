package com.example.lab5.Controller;

import com.example.lab5.Model.Category;
import com.example.lab5.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable int id)
    {
        return categoryService.findById(id);
    }
    @PostMapping
    public Category saveCategory(@RequestBody Category category){
        return categoryService.saveCategory(category);
    }
   /* @PutMapping("/{id}")
    public Category updateCategory(@PathVariable int id,@RequestBody Category category){
        return categoryService.updateCategory(id,category);
    }*/
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id){
        categoryService.deleteCategory(id);
    }

}
