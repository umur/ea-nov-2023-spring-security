package com.ea.dataone.service;

import com.ea.dataone.dto.ProductDto;

import java.util.List;

public interface ProductService {
    void create(ProductDto product);

    List<ProductDto> findAll();

    void update(ProductDto product);

    ProductDto getProduct(Long id);

    void delete(ProductDto product);

    List<ProductDto> findAllByPriceGreaterThan(int minPrice);

    List<ProductDto> findAllByNameContains(String keyword);
}
