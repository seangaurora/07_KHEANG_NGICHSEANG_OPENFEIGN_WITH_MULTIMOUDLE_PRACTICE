package com.seang.service.serviceImp;

import com.seang.CustomNotFoundException;
import com.seang.model.Customer;
import com.seang.model.CustomerRequest;
import com.seang.repository.CustomerRepository;
import com.seang.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImp implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(CustomerRequest customerRequest) {
        return customerRepository.save(customerRequest.toCustomer());
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new CustomNotFoundException("Customer with ID " + id + " does not exist"));
    }

    @Override
    public Customer updateCustomerById(Long id, CustomerRequest customerRequest) {
        return customerRepository.save(customerRequest.toCustomer(id));
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
