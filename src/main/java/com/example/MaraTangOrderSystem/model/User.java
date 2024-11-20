package com.example.MaraTangOrderSystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    protected User() {}
    public User(String name, String phoneNumber, List<Order> orders) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.orders = orders;
    }
}

