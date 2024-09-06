package com.seang.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private String name;
    private String email;

    public Customer toCustomer(){
        return new Customer(null, name, email);
    }

    public Customer toCustomer(Long id){
        return new Customer(id, name, email);
    }
}
