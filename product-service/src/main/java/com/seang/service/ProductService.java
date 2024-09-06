package com.seang.service;

import com.seang.model.Product;
import com.seang.model.ProductRequest;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductRequest productRequest);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product updateProductById(Long id, ProductRequest productRequest);

    void deleteProductById(Long id);
}
