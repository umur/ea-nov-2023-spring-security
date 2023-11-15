package edu.miu.springsecurity1.service.impl;

import edu.miu.springsecurity1.entity.Product;
import edu.miu.springsecurity1.repository.ProductRepo;
import edu.miu.springsecurity1.repository.UserRepo;
import edu.miu.springsecurity1.service.AuthService;
import edu.miu.springsecurity1.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final AuthService authService;
    private final UserRepo userRepo;

    @Override
    public void save(Product p) {
        Product product = Optional.ofNullable(p.getId()).map(id -> {
            var productDB = productRepo.findById(id).map(productEntity -> productEntity).orElse(p);
            productDB.setName(p.getName());
            productDB.setPrice(p.getPrice());
            return productDB;
        }).orElse(p);
        var user = authService.getCurrentlyLoggedInUser();
        product.setUser(userRepo.findByEmail(user.getUsername()));
        productRepo.save(p);
    }

    @Override
    public void update(Product p, int id) {
        p.setId(id);
        save(p);
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
