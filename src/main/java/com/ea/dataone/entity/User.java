package com.ea.dataone.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @OneToOne
    @JoinColumn(name="address_id")
    private Address address;
    @OneToMany
    @JoinColumn(name="user_id")
    private List<Review> reviews;

}
