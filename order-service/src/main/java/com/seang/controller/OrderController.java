package com.seang.controller;

import com.seang.ApiResponse;
import com.seang.model.OrderRequest;
import com.seang.model.OrderResponse;
import com.seang.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(ApiResponse.<OrderResponse>builder()
                .message("An order is created successfully!")
                .payload(orderService.createOrder(orderRequest))
                .status(HttpStatus.CREATED)
                .dateTime(LocalDateTime.now())
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getAllOrders() {
        return ResponseEntity.ok(ApiResponse.<List<OrderResponse>>builder()
                .message("All orders are fetched successfully!")
                .payload(orderService.getAllOrders())
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build());
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.<OrderResponse>builder()
                .message("The order with ID " + id +" is fetched successfully!")
                .payload(orderService.getOrderById(id))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build());
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> updateOrderById(@PathVariable Long id, @RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(ApiResponse.<OrderResponse>builder()
                .message("The order with ID " + id + " is updated successfully!")
                .payload(orderService.updateOrderById(id, orderRequest))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> deleteOrderById(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.ok(ApiResponse.<OrderResponse>builder()
                .message("The order with ID " + id + " is deleted successfully!")
                .payload(null)
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build());
    }

}
