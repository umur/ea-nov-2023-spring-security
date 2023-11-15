package edu.miu.jwtsecurity.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String name;
    private float price;


    @OneToOne
    @JoinColumn(name = "id_user")
    private User owner;
}
