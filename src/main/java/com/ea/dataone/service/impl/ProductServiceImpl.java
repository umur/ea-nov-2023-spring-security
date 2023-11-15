package com.ea.dataone.service.impl;

import com.ea.dataone.dto.ProductDto;
import com.ea.dataone.service.ProductService;
import com.ea.dataone.entity.Product;
import com.ea.dataone.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;

    private final ProductRepo productRepo;

    public void create(ProductDto product) {
        productRepo.save(modelMapper.map(product, Product.class));
    }

    public List<ProductDto> findAll() {
        return productRepo.findAll().stream()
                .map(product ->modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void update(ProductDto product) {
        productRepo.save(modelMapper.map(product, Product.class));
    }

    @Override
    public ProductDto getProduct(Long id) {
        Product product = productRepo.findById(id).orElse(null);
        if(product == null) return null;
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public void delete(ProductDto product) {
        productRepo.delete(modelMapper.map(product, Product.class));
    }

    @Override
    public List<ProductDto> findAllByPriceGreaterThan(int minPrice) {
        return productRepo.findAllByPriceGreaterThan(minPrice).stream()
                .map(product ->modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findAllByNameContains(String keyword) {
        return productRepo.findAllByNameContains(keyword).stream()
                .map(product ->modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }
}
