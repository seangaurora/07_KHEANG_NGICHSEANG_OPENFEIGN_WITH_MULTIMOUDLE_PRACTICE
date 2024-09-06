package com.seang.client;

import com.seang.ApiResponse;
import com.seang.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "http://localhost:8081/api/v1/customers")
public interface CustomerFeignClient {
    @GetMapping("{id}")
    ResponseEntity<ApiResponse<Customer>> getCustomerById(@PathVariable Long id);
}
