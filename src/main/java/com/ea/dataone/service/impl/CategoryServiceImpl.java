package com.ea.dataone.service.impl;

import com.ea.dataone.dto.CategoryDto;
import com.ea.dataone.service.CategoryService;
import com.ea.dataone.entity.Category;
import com.ea.dataone.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper modelMapper;

    private final CategoryRepo categoryRepo;

    public void create(CategoryDto category) {
        categoryRepo.save(modelMapper.map(category, Category.class));
    }

    public List<CategoryDto> findAll() {
        return categoryRepo.findAll().stream()
                .map(category ->modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void update(CategoryDto category) {
        categoryRepo.save(modelMapper.map(category, Category.class));
    }

    @Override
    public CategoryDto getCategory(Long id) {
        Category category = categoryRepo.findById(id).orElse(null);
        if(category == null) return null;
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public void delete(CategoryDto category) {
        categoryRepo.delete(modelMapper.map(category, Category.class));
    }

    @Override
    public CategoryDto findByName(String name) {
        return modelMapper.map(categoryRepo.findByName(name),
                CategoryDto.class);
    }
}
