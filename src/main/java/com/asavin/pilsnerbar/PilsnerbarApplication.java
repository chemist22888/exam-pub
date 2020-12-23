package com.asavin.pilsnerbar;

import com.asavin.pilsnerbar.entity.Order;
import com.asavin.pilsnerbar.entity.Product;
import com.asavin.pilsnerbar.entity.User;
import com.asavin.pilsnerbar.repository.OrderRepository;
import com.asavin.pilsnerbar.repository.ProductRepository;
import com.asavin.pilsnerbar.repository.UserRepository;
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
    public static void main(String[] args) {
        SpringApplication.run(PilsnerbarApplication.class, args);
    }

//    @Bean
//    InitializingBean sendDatabase() {
//        return () -> {
//            User u1 = userRepository.save(new User("John",true,true,10000l,passwordEncoder.encode("pass")));
//            User u = userRepository.save(new User("Lock",true,true,2l,passwordEncoder.encode("aa")));
//            Product p1 = productRepository.save(new Product("beer",12d,true));
//            Product p2 = productRepository.save(new Product("water",12d,true));
//            Product p3 = productRepository.save(new Product("milk",12d,true));
//
//
//            Order order = orderRepository.save(new Order(p1,u));
//            Order order2 = orderRepository.save(new Order(p1,u));
//            Order order3= orderRepository.save(new Order(p2,u));
//            Order order4= orderRepository.save(new Order(p3,u));
//            Order order5= orderRepository.save(new Order(p3,u));
//            Order order6= orderRepository.save(new Order(p3,u));
//            Order order7= orderRepository.save(new Order(p3,u));
//            Order order8= orderRepository.save(new Order(p1,u1));
//            Order order9= orderRepository.save(new Order(p1,u1));
//            Order order10= orderRepository.save(new Order(p1,u1));
//            Order order11= orderRepository.save(new Order(p3,u1));
//
//        };
//    }
}
