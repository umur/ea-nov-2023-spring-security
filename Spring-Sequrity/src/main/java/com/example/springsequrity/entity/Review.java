package com.example.springsequrity.entity;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String comment;
    private int numberOfStars;

    @ManyToOne
    private Product product;

}
