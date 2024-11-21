package com.example.MaraTangOrderSystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String phoneNumber;

    @Column(nullable = false)
    private Integer spicinessLevel;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    protected User() {}

    public User(String name, String phoneNumber, Integer spicinessLevel, List<Order> orders) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.spicinessLevel = spicinessLevel;
        this.orders = orders;
    }
}

