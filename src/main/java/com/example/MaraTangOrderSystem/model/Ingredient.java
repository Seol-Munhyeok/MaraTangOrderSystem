package com.example.MaraTangOrderSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="ingredients")
@Getter
@ToString
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    protected Ingredient() {}
    public Ingredient(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}
