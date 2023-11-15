package com.example.springsequrity.service.impl;

import com.example.springsequrity.entity.Product;
import com.example.springsequrity.repository.ProductRepo;
import com.example.springsequrity.service.AuthService;
import com.example.springsequrity.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final AuthService authService;

    @Override
    public void save(Product p) {
        var user = authService.getCurrentlyLoggedInUser();
        p.setUser(user);
        productRepo.save(p);
    }

    @Override
    public void delete(int id) {
        productRepo.deleteById(id);
    }

    @Override
    public Product getById(int id) {
        return productRepo.findById(id).get();
    }

    public List<Product> getAll() {
        var result = new ArrayList<Product>();
        productRepo.findAll().forEach(result::add);
        return result;
    }
}
