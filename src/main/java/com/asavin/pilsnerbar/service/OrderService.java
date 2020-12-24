package com.asavin.pilsnerbar.service;

import com.asavin.pilsnerbar.entity.Order;
import com.asavin.pilsnerbar.entity.OrderSummary;
import com.asavin.pilsnerbar.entity.Product;
import com.asavin.pilsnerbar.entity.User;

import java.util.List;
import java.util.Map;

public interface OrderService {
    void buy(User from, Product product) throws Exception;
    List<Order> findAll();
//    List<Order> findAllWithProduct(Product product);
//    List<Order>findAllFromUser(User user);
    List<OrderSummary> orderSummary(List<Order>orders);
    Map<Product, List<OrderSummary>> orderSummaryProduct();
}
