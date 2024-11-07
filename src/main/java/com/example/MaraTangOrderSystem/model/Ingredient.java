package com.example.MaraTangOrderSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@Getter
@ToString
public class Ingredient {
    private String name;
    private Integer price;

    public Ingredient(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}
