package com.example.MaraTangOrderSystem.repository;

import com.example.MaraTangOrderSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
