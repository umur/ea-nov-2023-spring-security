package edu.miu.springsecurity1.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;


import java.util.List;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    @NotBlank(message = "Name cannot be blank")
    private String name;
    private float price;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    //@JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //@JsonManagedReference
    @ManyToMany(mappedBy = "products")
    private List<Category> categories;

}
