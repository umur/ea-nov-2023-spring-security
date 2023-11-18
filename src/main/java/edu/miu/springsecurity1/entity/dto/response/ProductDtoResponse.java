package edu.miu.springsecurity1.entity.dto.response;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.List;

@Data
public class ProductDtoResponse {
    private Integer id;
    private String name;
    private float price;
    @JsonManagedReference
    private List<CategoryDtoResponse> categories;
}
