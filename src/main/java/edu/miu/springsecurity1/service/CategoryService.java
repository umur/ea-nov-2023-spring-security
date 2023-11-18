package edu.miu.springsecurity1.service;

import edu.miu.springsecurity1.entity.dto.request.CategoryDtoRequest;
import edu.miu.springsecurity1.entity.dto.response.CategoryDtoResponse;

import java.util.List;

public interface CategoryService {
    void save(CategoryDtoRequest p);

    List<CategoryDtoResponse> getAll();

    Object getById(int id);

    void delete(int id);
}
