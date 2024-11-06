package com.example.MaraTangOrderSystem.controller;

import com.example.MaraTangOrderSystem.model.Ingredient;
import com.example.MaraTangOrderSystem.model.IngredientType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IngredientController {
    @GetMapping("/ingredients")
    public String showIngredients(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();

        for (IngredientType type : IngredientType.values()) {
            Ingredient ingredient = new Ingredient(type.getName(), type.getPrice());
            ingredients.add(ingredient);
        }

        model.addAttribute("ingredients", ingredients);
        return "ingredients";
    }
}
