package com.seang.controller;

import com.seang.ApiResponse;
import com.seang.model.Product;
import com.seang.model.ProductRequest;
import com.seang.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(ApiResponse.<Product>builder()
                .message("A product is created successfully!")
                .payload(productService.createProduct(productRequest))
                .status(HttpStatus.CREATED)
                .dateTime(LocalDateTime.now())
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        return ResponseEntity.ok(ApiResponse.<List<Product>>builder()
                .message("All products are fetched successfully!")
                .payload(productService.getAllProducts())
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build());
    }


    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.<Product>builder()
                .message("The product with ID " + id +" is fetched successfully!")
                .payload(productService.getProductById(id))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build());
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<Product>> updateProductById(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(ApiResponse.<Product>builder()
                .message("The product with ID " + id + " is updated successfully!")
                .payload(productService.updateProductById(id, productRequest))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Product>> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok(ApiResponse.<Product>builder()
                .message("The product with ID " + id + " is deleted successfully!")
                .payload(null)
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build());
    }


}
