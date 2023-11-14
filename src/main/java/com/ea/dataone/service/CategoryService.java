package com.ea.dataone.service;

import com.ea.dataone.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    void create(CategoryDto category);

    List<CategoryDto> findAll();

    void update(CategoryDto category);

    CategoryDto getCategory(Long id);

    void delete(CategoryDto category);

    CategoryDto findByName(String name);
}
