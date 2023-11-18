package ea.lab6.controller;

import ea.lab6.dto.ProductDto;
import ea.lab6.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final  ProductService productService;
    @PostMapping
    public ProductDto addProduct(@RequestBody ProductDto productDto) throws Exception {
        ProductDto productDto1 = productService.addProduct(productDto);
        System.out.println("API Complete " + productDto1.getName());
        return productDto1;
    }
}
