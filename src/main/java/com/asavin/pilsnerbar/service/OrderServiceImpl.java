package com.asavin.pilsnerbar.service;

import com.asavin.pilsnerbar.entity.Order;
import com.asavin.pilsnerbar.entity.OrderSummary;
import com.asavin.pilsnerbar.entity.Product;
import com.asavin.pilsnerbar.entity.User;
import com.asavin.pilsnerbar.repository.OrderRepository;
import com.asavin.pilsnerbar.repository.ProductRepository;
import com.asavin.pilsnerbar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;


    @Override
    public void buy(User user, Product product) throws Exception {
        user = userRepository.findById(user.getId()).get();
        if(user.isActive()) {
            if (product!=null && product.getPrice() == null)
                product = productRepository.findById(product.getId()).get();

            if(user.getPocket()>=product.getPrice())
                if(!product.isForAdult() || user.isAdult()){
                    orderRepository.save(new Order(product, user));
                    user.setPocket((long) (user.getPocket()-product.getPrice()));
                    userRepository.save(user);

                    return;
                }
        }
        throw new Exception();
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
    @Override
    public List<OrderSummary> orderSummary() {
        List<Order> orders = findAll();
        Map<Product, Long> collected =
                orders.stream().collect(
                        Collectors.groupingBy(Order::getProduct, Collectors.counting()));

        List<OrderSummary> result = collected.keySet().stream().map(product -> new OrderSummary(product,
                collected.get(product),
                collected.get(product) * product.getPrice())).
                collect(Collectors.toList());

        return result;
    }

    @Override
    public Map<Product, List<OrderSummary>> orderSummaryProduct() {
        List<Order> orders = findAll();
        Map<Product, List<Order>> groupedOrders = orders.stream().collect(Collectors.groupingBy(Order::getProduct));

        Map<Product, List<OrderSummary>> res = groupedOrders.entrySet().stream().collect(Collectors.toMap(
                entry -> entry.getKey(),
                entry -> entry.getValue().stream().collect
                        (Collectors.groupingBy(Order::getFromUser, Collectors.counting())).
                        entrySet().stream().map(groupEntry -> new OrderSummary(
                            groupEntry.getKey(), groupEntry.getValue(),
                            groupEntry.getValue() * entry.getKey().getPrice())
                ).collect(Collectors.toList())));
        return res;
    }
}
