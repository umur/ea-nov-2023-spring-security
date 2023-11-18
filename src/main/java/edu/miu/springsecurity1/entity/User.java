package edu.miu.springsecurity1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;
    private String password;
    private String firstname;
    private String lastname;

    @JsonBackReference
    @OneToMany(mappedBy = "user")
    private List<Product> product;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;
}
