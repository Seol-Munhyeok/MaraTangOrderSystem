package com.example.MaraTangOrderSystem.dto.Order;

import java.util.List;

public record OrderSummaryDto(
        Long userId,
        Integer spicinessLevel,
        List<IngredientDetailDto> ingredientDetailDtos,
        Integer subtotal
) {}
