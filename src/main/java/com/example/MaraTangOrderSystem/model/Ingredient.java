package com.example.MaraTangOrderSystem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="ingredients")
@Getter
@ToString


public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer price;

    protected Ingredient() {}
    public Ingredient(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}
