package com.example.MaraTangOrderSystem.dto.Order;

import java.util.List;

public record ReceiptDto (
    Long userId,
    String userName,
    List<OrderSummaryDto> orderSummaries,
    Integer totalPrice
) {}



