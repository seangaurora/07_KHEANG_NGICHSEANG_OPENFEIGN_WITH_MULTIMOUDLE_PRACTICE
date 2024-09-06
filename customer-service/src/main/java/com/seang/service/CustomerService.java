package com.seang.service;

import com.seang.model.Customer;
import com.seang.model.CustomerRequest;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(CustomerRequest customerRequest);

    List<Customer> getAllCustomers();

    Customer getCustomerById(Long id);

    Customer updateCustomerById(Long id, CustomerRequest customerRequest);

    void deleteCustomerById(Long id);
}
