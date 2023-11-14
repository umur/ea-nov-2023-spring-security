package com.example.lab5.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "user_table")
public class User {
    @Id
    private int id;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Review> reviews;
    @Embedded
    private Address address;
}
