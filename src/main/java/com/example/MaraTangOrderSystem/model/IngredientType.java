package com.example.MaraTangOrderSystem.model;

import lombok.Getter;

@Getter
public enum IngredientType {
    BEEF("소고기", 2000),
    LAMB_MEAT("양고기", 3000),
    PEANUT_SAUCE("땅콩소스", 500),
    CHILI_OIL("고추기름", 500),
    CHUNG_GYEONG_CHAE("청경채", 2000),
    RAMYEON_NOODLES("라면사리", 1000),
    SSUK_GAT("쑥갓", 2000),
    SAESONG_I_MUSHROOM("새송이 버섯", 2000),
    DRIED_TOFU("건두부", 2000),
    FRIED_TOFU("유부", 1000);

    private final String name;
    private final int price;
    IngredientType(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
