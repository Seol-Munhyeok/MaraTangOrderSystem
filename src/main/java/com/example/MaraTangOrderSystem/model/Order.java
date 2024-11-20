package com.example.MaraTangOrderSystem.model;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(columnDefinition = "INTEGER CHECK (spiciness_level BETWEEN 0 AND 3)")
    private Integer spicinessLevel;

    private Integer totalPrice;

    protected Order() {}

    public Order(User user, Ingredient ingredient, Integer quantity, Integer totalPrice, Integer spicinessLevel) {
        this.user = user;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.spicinessLevel = spicinessLevel;
        this.totalPrice = totalPrice;
    }
}
