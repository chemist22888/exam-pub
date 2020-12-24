package com.asavin.pilsnerbar.controller;

import com.asavin.pilsnerbar.entity.Order;
import com.asavin.pilsnerbar.entity.OrderSummary;
import com.asavin.pilsnerbar.entity.Product;
import com.asavin.pilsnerbar.entity.User;
import com.asavin.pilsnerbar.json.OrderView;
import com.asavin.pilsnerbar.json.UserView;
import com.asavin.pilsnerbar.service.OrderService;
import com.asavin.pilsnerbar.service.ProductService;
import com.asavin.pilsnerbar.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    @JsonView(UserView.UserShortInfoView.class)
    List<User> getUsers() {
        return userService.findAll();
    }
    @JsonView(UserView.UserFullInfoView.class)
    @GetMapping("/users/{id}")
    ObjectNode getUser(@PathVariable Long id){
        ObjectMapper objectMapper = new ObjectMapper();
        User user = userService.findById(id);
        List<OrderSummary> userOrderSummary = orderService.orderSummary(user.getOrders());

        ObjectNode objectNode = objectMapper.valueToTree(user);
        objectNode.set("orders",objectMapper.valueToTree(userOrderSummary));
        return objectNode;
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
        return orderService.orderSummary(orderService.findAll());
    }
    @JsonView(OrderView.SummaryProductView.class)
    @GetMapping(value = "/summary/product",produces = MediaType.APPLICATION_JSON_VALUE)
    Map<Product,List<OrderSummary>> findProductOrders(){
        return orderService.orderSummaryProduct();
    }
    @GetMapping("/summary/user")
    List<Order> findProductUser(){
        return orderService.findAll();
    }
}
