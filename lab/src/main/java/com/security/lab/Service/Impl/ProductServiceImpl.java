package com.security.lab.Service.Impl;

import com.security.lab.Dto.ProductDto;
import com.security.lab.Entity.Product;
import com.security.lab.Repository.ProductRepo;
import com.security.lab.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo repo;
    private final ModelMapper modelMapper;
    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = repo.findAll();
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public ProductDto getOneProduct(Integer id) {
        Optional<Product> productOptional = repo.findById(id);
        ProductDto productDto = new ProductDto();
        if(productOptional.isPresent()) {
            productDto.setName(productOptional.get().getName());
            productDto.setQuantity(productOptional.get().getQuantity());
            productDto.setPrice(productOptional.get().getPrice());
        }
        return productDto;
    }
}
