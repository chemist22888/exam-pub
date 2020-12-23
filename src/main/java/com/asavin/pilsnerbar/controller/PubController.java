package com.asavin.pilsnerbar.controller;

import com.asavin.pilsnerbar.entity.Order;
import com.asavin.pilsnerbar.entity.OrderSummary;
import com.asavin.pilsnerbar.entity.Product;
import com.asavin.pilsnerbar.entity.User;
import com.asavin.pilsnerbar.json.OrderView;
import com.asavin.pilsnerbar.service.OrderService;
import com.asavin.pilsnerbar.service.ProductService;
import com.asavin.pilsnerbar.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PubController {
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;

    @GetMapping("/users")
    List<User> getUsers(){
        return userService.findAll();
    }
    @GetMapping("/user/{id}")
    User getUser(@PathVariable Long id){
        return userService.findById(id);
    }
    @GetMapping("/drink-menu")
    List<Product>drinkMenu(){
        return productService.findAll();
    }
    @PostMapping("/buy")
    @ResponseBody
    ResponseEntity buy(@RequestParam ("ProductId")Long productId){
        try {
            orderService.buy(userService.getAuthorizedUser(),
                    new Product(productId));
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/summary/all")
    @JsonView(OrderView.SummaryAllView.class)
    List<OrderSummary> findAllOrders(){
        return orderService.orderSummary();
    }
    @JsonView(OrderView.SummaryProductView.class)
    @GetMapping("/summary/product")
    Map<Product,List<OrderSummary>> findProductOrders(){
        return orderService.orderSummaryProduct();
    }
    @GetMapping("/summary/user")
    List<Order> findProductUser(){
        return orderService.findAll();
    }
}
