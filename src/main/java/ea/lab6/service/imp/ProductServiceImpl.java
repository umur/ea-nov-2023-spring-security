package ea.lab6.service.imp;

import ea.lab6.dto.ProductDto;
import ea.lab6.entity.Product;
import ea.lab6.entity.User;
import ea.lab6.repository.ProductRepository;
import ea.lab6.service.ProductService;
import ea.lab6.service.UserService;
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
