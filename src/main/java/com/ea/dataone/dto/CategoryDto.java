package com.ea.dataone.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CategoryDto {
    private Long id;
    private String name;
    private List<ProductDto> products;
}
