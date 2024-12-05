package com.example.MaraTangOrderSystem.converter;

import com.example.MaraTangOrderSystem.dto.Order.OrderRequestDto;
import com.example.MaraTangOrderSystem.dto.Order.OrderResponseDto;
import com.example.MaraTangOrderSystem.dto.Order.OrderDetailDto;
import com.example.MaraTangOrderSystem.model.Ingredient;
import com.example.MaraTangOrderSystem.model.Order;
import com.example.MaraTangOrderSystem.model.OrderDetail;
import com.example.MaraTangOrderSystem.model.User;
import com.example.MaraTangOrderSystem.repository.IngredientRepository;

import java.util.List;
import java.util.stream.Collectors;

public class OrderConverter {
    public static OrderResponseDto convertToOrderResponseDto(Order order) {
        return new OrderResponseDto(
                order.getId(),
                order.getUser().getId(),
                order.getTotalPrice()
        );
    }

    public static OrderRequestDto convertToOrderRequestDto(Order order) {
        List<OrderDetailDto> orderDetailDtos = order.getOrderDetails().stream()
                .map(OrderDetailConverter::convertToOrderDetailDto)
                .collect(Collectors.toList());

        return new OrderRequestDto(
                order.getId(),
                order.getSpicinessLevel(),
                orderDetailDtos
        );
    }

    public static Order convertToOrder(OrderRequestDto orderRequestDto, User user, IngredientRepository ingredientRepository) {
        Order order = new Order(user, 0, orderRequestDto.spicinessLevel());

        List<OrderDetailDto> orderDetailDtos = orderRequestDto.orderDetails();
        if (orderDetailDtos != null && !orderDetailDtos.isEmpty()) {
            for (OrderDetailDto detailDto : orderDetailDtos) {
                Ingredient ingredient = ingredientRepository.findById(detailDto.ingredientId())
                        .orElseThrow(() -> new RuntimeException("해당 재료가 없습니다."));
                OrderDetail orderDetail = new OrderDetail(order, ingredient, detailDto.quantity());
                order.getOrderDetails().add(orderDetail);
            }
        }
        return order;
    }

}
