package com.example.backend1.service;

import com.example.backend1.dao.ProductRepository;
import com.example.backend1.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    //save book
    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    //read Books
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductsById(int id) {
        Optional<Product> prodfound = productRepository.findById(id);

        if (prodfound.isPresent()) {
            Product targetProduct = prodfound.get();
            return targetProduct;
        }else{
            return null;
        }


    }

    //Delete Book
    @Override
    public void deleteProductById(int id) {
        productRepository.deleteById(id);

    }

    //Update Bookd
    @Override
    public Product updateProductById(int id, Product product) {

        Optional<Product> prodfound = productRepository.findById(id);

        if (prodfound.isPresent()) {
            Product updateProduct = prodfound.get();

            updateProduct.setTitle(product.getTitle());
            updateProduct.setName(product.getName());

            // Save the updated book back to the database
            Product updated = productRepository.save(updateProduct);
            return updated;
        }
        else {
            return null;
        }
    }


}

