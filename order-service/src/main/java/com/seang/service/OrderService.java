package com.seang.service;

import com.seang.model.OrderRequest;
import com.seang.model.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(Long id);

    OrderResponse updateOrderById(Long id, OrderRequest orderRequest);

    void deleteOrderById(Long id);
}
