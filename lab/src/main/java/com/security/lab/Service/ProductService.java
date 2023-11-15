package com.security.lab.Service;

import com.security.lab.Dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();

    ProductDto getOneProduct(Integer id);
}
