package edu.miu.springsecurity1.service;


import edu.miu.springsecurity1.entity.Product;
import edu.miu.springsecurity1.entity.dto.request.ProductDtoRequest;
import edu.miu.springsecurity1.entity.dto.response.ProductDtoResponse;

import java.util.List;

public interface ProductService {

    ProductDtoResponse save(ProductDtoRequest p);

    void delete(int id);

    Product getById(int id);

    List<ProductDtoResponse> getAll();
}
