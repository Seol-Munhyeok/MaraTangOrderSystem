package com.example.MaraTangOrderSystem.model;

import lombok.Getter;

@Getter
public enum IngredientType {
    BEEF("BEEF", 2000),
    LAMB_MEAT("LAMB_MEAT", 3000),
    PEANUT_SAUCE("PEANUT_SAUCE", 500),
    CHILI_OIL("CHILI_OIL", 500),
    CHUNG_GYEONG_CHAE("CHUNG_GYEONG_CHAE", 2000),
    RAMYEON_NOODLES("RAMYEON_NOODLES", 1000),
    SSUK_GAT("SSUK_GAT", 2000),
    SAESONG_I_MUSHROOM("SAESONG_I_MUSHROOM", 2000),
    DRIED_TOFU("DRIED_TOFU", 2000),
    FRIED_TOFU("FRIED_TOFU", 1000);

    private final String name;
    private final int price;
    IngredientType(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
