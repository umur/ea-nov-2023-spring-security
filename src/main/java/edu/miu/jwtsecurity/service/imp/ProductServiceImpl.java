package edu.miu.jwtsecurity.service.imp;

import edu.miu.jwtsecurity.dto.ProductDto;
import edu.miu.jwtsecurity.model.Product;
import edu.miu.jwtsecurity.model.User;
import edu.miu.jwtsecurity.repository.ProductRepository;
import edu.miu.jwtsecurity.service.ProductService;
import edu.miu.jwtsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductServiceImpl  implements ProductService {
    private final ProductRepository productRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public ProductDto addProduct(ProductDto productDto) throws Exception {
        User userFromSpringContext = userService.getUserFromSpringContext();
        Product product = modelMapper.map(productDto, Product.class);
        product.setOwner(userFromSpringContext);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct,ProductDto.class);
    }
}
