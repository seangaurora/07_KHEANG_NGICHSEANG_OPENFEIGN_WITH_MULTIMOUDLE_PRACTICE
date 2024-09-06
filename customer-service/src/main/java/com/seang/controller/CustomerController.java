package com.seang.controller;

import com.seang.ApiResponse;
import com.seang.model.Customer;
import com.seang.model.CustomerRequest;
import com.seang.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<ApiResponse<Customer>> createCustomer(@RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.ok(ApiResponse.<Customer>builder()
                .message("A customer is created successfully!")
                .payload(customerService.createCustomer(customerRequest))
                .status(HttpStatus.CREATED)
                .dateTime(LocalDateTime.now())
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Customer>>> getAllCustomers() {
        return ResponseEntity.ok(ApiResponse.<List<Customer>>builder()
                .message("All customers are fetched successfully!")
                .payload(customerService.getAllCustomers())
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build());
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Customer>> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.<Customer>builder()
                .message("The customer with ID " + id +" is fetched successfully!")
                .payload(customerService.getCustomerById(id))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build());
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<Customer>> updateCustomerById(@PathVariable Long id, @RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.ok(ApiResponse.<Customer>builder()
                .message("The customer with ID " + id + " is updated successfully!")
                .payload(customerService.updateCustomerById(id, customerRequest))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Customer>> deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.ok(ApiResponse.<Customer>builder()
                .message("The customer with ID " + id + " is deleted successfully!")
                .payload(null)
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build());
    }

}
