package com.ea.dataone.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ProductDto {

    private Long id;
    private String name;
    private double price;
    private double rating;
    private List<ReviewDto> reviews;
}
