package edu.miu.springsecurity1.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDtoRequest {
private String name;
private int price;
}
