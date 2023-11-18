package edu.miu.springsecurity1.entity.dto.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.miu.springsecurity1.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDtoResponse {
    private Integer id;
    private String name;

    @JsonBackReference
    private List<Product> products;

}
