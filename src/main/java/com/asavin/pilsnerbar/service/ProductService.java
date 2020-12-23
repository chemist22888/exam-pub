package com.asavin.pilsnerbar.service;

import com.asavin.pilsnerbar.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product addProduct(Product product);
    Product findProductById(Long id);
}
