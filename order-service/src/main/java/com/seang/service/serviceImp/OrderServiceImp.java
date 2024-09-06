package com.seang.service.serviceImp;

import com.seang.CustomNotFoundException;
import com.seang.client.CustomerFeignClient;
import com.seang.client.ProductFeignClient;
import com.seang.model.*;
import com.seang.repository.OrderRepository;
import com.seang.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImp implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerFeignClient customerFeignClient;
    private final ProductFeignClient productFeignClient;

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setCustomerId(orderRequest.getCustomerId());
        order.setOrderDate(LocalDateTime.now());
        order.setProductIds(orderRequest.getProductIds());
        orderRepository.save(order);
        return toOrderResponse(order);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        Iterable<Order> orderList = orderRepository.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orderList) {
            orderResponses.add(toOrderResponse(order));
        }
        return orderResponses;
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new CustomNotFoundException("Order with ID " + id + " does not exist"));
        return toOrderResponse(order);
    }

    @Override
    public OrderResponse updateOrderById(Long id, OrderRequest orderRequest) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new CustomNotFoundException("Order with ID " + id + " does not exist"));
        order.setOrderDate(LocalDateTime.now());
        order.setProductIds(orderRequest.getProductIds());
        order.setCustomerId(orderRequest.getCustomerId());
        return toOrderResponse(order);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    OrderResponse toOrderResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();

        //get required customer
        Customer customer = customerFeignClient.getCustomerById(order.getCustomerId()).getBody().getPayload();
        if (customer == null) {
            throw new CustomNotFoundException("Customer does not exist!");
        }

        //get desired product list
        List<Product> products = new ArrayList<>();
        List<Long> productIds = order.getProductIds();
        for (Long productId : productIds) {
            Product product = productFeignClient.getProductById(productId).getBody().getPayload();
            products.add(product);
        }

        //set all field to order response (convert form Order -> OrderResponse)
        orderResponse.setId(order.getId());
        orderResponse.setOrderDate(order.getOrderDate());
        orderResponse.setCustomerResponse(customer);
        orderResponse.setProductResponses(products);

        return orderResponse;
    }
}
