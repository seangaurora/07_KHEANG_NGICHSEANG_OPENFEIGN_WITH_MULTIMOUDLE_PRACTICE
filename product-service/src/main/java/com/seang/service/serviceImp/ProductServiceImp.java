package com.seang.service.serviceImp;

import com.seang.CustomNotFoundException;
import com.seang.model.Product;
import com.seang.model.ProductRequest;
import com.seang.repository.ProductRepository;
import com.seang.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product createProduct(ProductRequest productRequest) {
        return productRepository.save(productRequest.toProduct());
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new CustomNotFoundException("Product with ID " + id + " does not exist"));
    }

    @Override
    public Product updateProductById(Long id, ProductRequest productRequest) {
        return productRepository.save(productRequest.toProduct(id));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
