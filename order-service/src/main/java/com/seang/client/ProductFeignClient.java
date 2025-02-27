package com.seang.client;

import com.seang.ApiResponse;
import com.seang.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:8082/api/v1/products")
public interface ProductFeignClient {
    @GetMapping("{id}")
    ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Long id);
}
