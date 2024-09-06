package com.seang.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private Double price;

    public Product toProduct(){
        return new Product(null, name, price);
    }
    public Product toProduct(Long id){
        return new Product(id, name, price);
    }

}
