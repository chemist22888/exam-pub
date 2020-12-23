package com.asavin.pilsnerbar;

import com.asavin.pilsnerbar.entity.Order;
import com.asavin.pilsnerbar.entity.OrderSummary;
import com.asavin.pilsnerbar.entity.Product;
import com.asavin.pilsnerbar.entity.User;
import com.asavin.pilsnerbar.service.OrderService;
import com.asavin.pilsnerbar.service.ProductService;
import com.asavin.pilsnerbar.service.UserService;
import org.junit.Before;
//import org.junit.jupiter.api.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
class PilsnerbarApplicationTests {

}
