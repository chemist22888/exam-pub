package com.asavin.pilsnerbar;

import com.asavin.pilsnerbar.entity.Order;
import com.asavin.pilsnerbar.entity.Product;
import com.asavin.pilsnerbar.entity.User;
import com.asavin.pilsnerbar.repository.OrderRepository;
import com.asavin.pilsnerbar.repository.ProductRepository;
import com.asavin.pilsnerbar.repository.UserRepository;
import com.asavin.pilsnerbar.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PilsnerbarApplication {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    OrderService orderService;
    public static void main(String[] args) {
        SpringApplication.run(PilsnerbarApplication.class, args);
    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            User u1 = userRepository.save(new User("John",true,true,10000l,passwordEncoder.encode("pass"),true));
            User u = userRepository.save(new User("Lock",true,true,200l,passwordEncoder.encode("aa")));
            Product p1 = productRepository.save(new Product("beer",12d,true));
            Product p2 = productRepository.save(new Product("water",12d,true));
            Product p3 = productRepository.save(new Product("milk",12d,true));

//            orderService.buy();

            orderService.buy(u, p1);
            orderService.buy(u, p1);
            orderService.buy(u, p2);
            orderService.buy(u, p3);
            orderService.buy(u, p3);
            orderService.buy(u, p3);
            orderService.buy(u, p3);
            orderService.buy(u1, p1);
            orderService.buy(u1, p1);
            orderService.buy(u1, p1);
            orderService.buy(u1, p3);

        };
    }
}
