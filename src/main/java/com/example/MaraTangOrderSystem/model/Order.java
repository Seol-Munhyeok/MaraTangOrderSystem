package com.example.MaraTangOrderSystem.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@ToString
@Entity
@Table(name="my_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ingredientName;
    private Integer ingredientPrice;
    private Integer quantity;
    private Integer totalPrice;

    protected Order() {}

    public Order(String ingredientName, Integer ingredientPrice, Integer quantity, Integer totalPrice) {
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
