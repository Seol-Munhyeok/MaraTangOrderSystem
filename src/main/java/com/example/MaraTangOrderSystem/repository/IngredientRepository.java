package com.example.MaraTangOrderSystem.repository;

import com.example.MaraTangOrderSystem.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
