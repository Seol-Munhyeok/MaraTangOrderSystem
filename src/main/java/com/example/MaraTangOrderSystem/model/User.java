package com.example.MaraTangOrderSystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
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

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Setter
    @Column(nullable = false)
    private String name;

    @Setter
    private String phoneNumber;

    @Setter
    private String profileImagePath;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    protected User() {}

    public static User create(String email, String password, String nickname, String name, String profileImagePath) {
        User user = new User();
        user.email = email;
        user.password = password;
        user.nickname = nickname;
        user.name = name;
        user.profileImagePath = profileImagePath;
        return user;
    }

    public String getPhoneNumber() {
        return phoneNumber != null ? phoneNumber : "N/A";
    }

    public String getProfileImagePath() {
        return profileImagePath != null ? profileImagePath : "/images/default-profile.png";
    }
}