package edu.miu.springsecurity1.service.impl;

import edu.miu.springsecurity1.entity.Product;
import edu.miu.springsecurity1.entity.dto.request.ProductDtoRequest;
import edu.miu.springsecurity1.entity.dto.response.CategoryDtoResponse;
import edu.miu.springsecurity1.entity.dto.response.ProductDtoResponse;
import edu.miu.springsecurity1.repository.ProductRepo;
import edu.miu.springsecurity1.repository.UserRepo;
import edu.miu.springsecurity1.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final ModelMapper modelMapper;
    private final UserRepo userRepo;
    @Override
    public ProductDtoResponse save(ProductDtoRequest p) {
        var product = modelMapper.map(p, Product.class);
        product.setId(null);
        var userDetails = (AwesomeUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var username =  userDetails.getUsername();
        product.setUser(userRepo.findByEmail(username));
        return modelMapper.map(productRepo.save(product), ProductDtoResponse.class);
    }

    @Override
    public void delete(int id) {
        productRepo.deleteById(id);
    }

    @Override
    public Product getById(int id) {
        return productRepo.findById(id).get();
    }

    public List<ProductDtoResponse> getAll() {
        var result = new ArrayList<ProductDtoResponse>();
        productRepo.findAll().forEach(p -> {
            var product =  modelMapper.map(p, ProductDtoResponse.class);
            product.setCategories(p.getCategories().stream()
                    .map(c -> modelMapper.map(c, CategoryDtoResponse.class))
                    .collect(Collectors.toList()));
            result.add(product);
        });
        return result;
    }
}
