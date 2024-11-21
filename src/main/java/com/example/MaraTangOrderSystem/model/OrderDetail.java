package com.example.MaraTangOrderSystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

@Entity
@Table(name = "order_detail")
@Getter
@ToString
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    private Integer quantity;

    protected OrderDetail() {}

    public OrderDetail(Order order, Ingredient ingredient, Integer quantity) {
        this.order = order;
        this.ingredient = ingredient;
        this.quantity = quantity;
    }
}

