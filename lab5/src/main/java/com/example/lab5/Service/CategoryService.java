package com.example.lab5.Service;


import com.example.lab5.Model.Category;

public interface CategoryService {
    Category findById(int id);
    Category saveCategory(Category category);
   // Category updateCategory(int id,Category category);
    void deleteCategory(int id);
}
