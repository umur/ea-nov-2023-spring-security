package edu.miu.springsecurity1.entity;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String role;
}
