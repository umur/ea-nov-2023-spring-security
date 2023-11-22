package edu.miu.ea.security.service.imp;

import edu.miu.ea.security.dto.ProductDTO;
import edu.miu.ea.security.model.Product;
import edu.miu.ea.security.model.User;
import edu.miu.ea.security.repository.ProductRepository;
import edu.miu.ea.security.service.ProductService;
import edu.miu.ea.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public ProductDTO addProduct(ProductDTO productDto) throws Exception {
        User userFromSpringContext = userService.getUserFromSpringContext();
        Product product = modelMapper.map(productDto, Product.class);
        product.setOwner(userFromSpringContext);
        Product savedProduct = repository.save(product);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

}
