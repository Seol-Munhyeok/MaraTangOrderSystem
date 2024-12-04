package com.example.MaraTangOrderSystem.converter;

import com.example.MaraTangOrderSystem.dto.Ingredient.IngredientDto;
import com.example.MaraTangOrderSystem.model.Ingredient;

public class IngredientConverter {
    public static IngredientDto convertToUserDto(Ingredient ingredient) {
        return new IngredientDto(
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getPrice(),
                ingredient.getOrderDetails());
    }
}
