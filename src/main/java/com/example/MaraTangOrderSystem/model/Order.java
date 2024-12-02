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

    @Column(nullable = false)
    private Integer spicinessLevel;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    @Setter
    private Integer totalPrice = 0;

    protected Order() {}

    public Order(User user, Integer totalPrice, Integer spicinessLevel) {
        this.user = user;
        this.totalPrice = totalPrice;
        this.spicinessLevel = (spicinessLevel != null) ? spicinessLevel : 0;
    }
}
