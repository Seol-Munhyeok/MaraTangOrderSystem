package com.example.MaraTangOrderSystem.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
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
}
