package edu.miu.springsecurity1.service.impl;

import edu.miu.springsecurity1.entity.Category;
import edu.miu.springsecurity1.entity.dto.request.CategoryDtoRequest;
import edu.miu.springsecurity1.entity.dto.response.CategoryDtoResponse;
import edu.miu.springsecurity1.repository.CategoryRepo;
import edu.miu.springsecurity1.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;

    @Override
    public void save(CategoryDtoRequest p) {
        categoryRepo.save(modelMapper.map(p, Category.class));
    }

    @Override
    public void delete(int id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public Category getById(int id) {
        return categoryRepo.findById(id).get();
    }

    public List<CategoryDtoResponse> getAll() {
        var result = new ArrayList<Category>();
        categoryRepo.findAll().forEach(result::add);
        return result.stream()
                .map(p -> modelMapper.map(p, CategoryDtoResponse.class))
                .collect(Collectors.toList());
    }
}
