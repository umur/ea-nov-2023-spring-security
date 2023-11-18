package ea.lab6.entity;


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


    @ManyToOne
    @JoinColumn(name = "id_user")
    private User owner;
}
