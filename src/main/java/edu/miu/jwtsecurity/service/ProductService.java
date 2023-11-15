package edu.miu.jwtsecurity.service;

import edu.miu.jwtsecurity.dto.ProductDto;

public interface ProductService {

    ProductDto addProduct(ProductDto productDto) throws Exception;
}
