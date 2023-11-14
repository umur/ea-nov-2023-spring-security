package com.example.lab5.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Entity
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue
    private int id;
   // private String firstName;
   // private String lastName;
    @Column(nullable = false,length = 50,unique = true)
    private String email;
    @Column(nullable = false,length = 65)
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Review> reviews;
   // @Embedded
  //  private Address address;
}
