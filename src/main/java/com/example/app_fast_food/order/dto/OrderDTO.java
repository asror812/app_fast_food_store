package com.example.app_fast_food.order.dto;

import com.example.app_fast_food.order.orderItem.OrderItem;
import com.example.app_fast_food.order.OrderStatus;
import com.example.app_fast_food.order.PaymentType;
import com.example.app_fast_food.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderDTO {


    private List<OrderItem> orderItem;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

}
