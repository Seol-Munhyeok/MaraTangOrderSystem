package com.example.MaraTangOrderSystem.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ingredientName;
    private Integer ingredientPrice;
    private Integer quantity;
    private Integer totalPrice;

    public Order(String ingredientName, Integer ingredientPrice, Integer quantity, Integer totalPrice) {
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

}
