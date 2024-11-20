package com.example.MaraTangOrderSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="orders")
@Getter
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    private Integer quantity;
    private Integer totalPrice;

    protected Order() {}

    public Order(User user, Ingredient ingredient, Integer quantity, Integer totalPrice) {
        this.user = user;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
