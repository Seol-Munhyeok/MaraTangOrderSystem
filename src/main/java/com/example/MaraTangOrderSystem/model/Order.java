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

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    private Integer totalPrice;

    protected Order() {}

    public Order(User user, Integer totalPrice) {
        this.user = user;
        this.totalPrice = totalPrice;
    }


}
