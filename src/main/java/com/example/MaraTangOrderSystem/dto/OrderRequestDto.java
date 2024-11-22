package com.example.MaraTangOrderSystem.dto;

import java.util.List;

// ingredientsIds[i]의 개수가 quantities[i]에 저장된다.
public record OrderRequestDto(Long userId, List<Long> ingredientIds, List<Integer> quantities) {}




