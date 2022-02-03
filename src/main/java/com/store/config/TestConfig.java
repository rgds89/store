package com.store.config;

import com.store.entities.*;
import com.store.entities.enums.OrderStatus;
import com.store.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRespository userRespository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;


    @Override
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", new BigDecimal("90.5"), "");
        Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas antess.", new BigDecimal("2190.0"), "");
        Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", new BigDecimal("1250.0"), "");
        Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", new BigDecimal("1200.0"), "");
        Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", new BigDecimal("100.99"), "");

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        p1.getCategories().add(cat2);
        p2.getCategories().add(cat1);
        p2.getCategories().add(cat3);
        p3.getCategories().add(cat3);
        p4.getCategories().add(cat3);
        p5.getCategories().add(cat2);

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        userRespository.saveAll(Arrays.asList(u1, u2));

        Order o1 = new Order(null, LocalDateTime.ofInstant(Instant.parse("2019-06-20T19:53:07Z"), ZoneOffset.UTC), OrderStatus.PAID, u1);
        Order o2 = new Order(null, LocalDateTime.ofInstant(Instant.parse("2019-07-21T03:42:10Z"), ZoneOffset.UTC), OrderStatus.WAITING_PAYMENT, u2);
        Order o3 = new Order(null, LocalDateTime.ofInstant(Instant.parse("2019-07-22T15:21:22Z"), ZoneOffset.UTC), OrderStatus.WAITING_PAYMENT, u1);

        orderRepository.saveAll(Arrays.asList(o1, o2, o3));

        OrderItem oi1 = new OrderItem(o1, p1, 2L, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 1L, p3.getPrice());
        OrderItem oi3 = new OrderItem(o2, p3, 2L, p3.getPrice());
        OrderItem oi4 = new OrderItem(o3, p5, 2L, p5.getPrice());

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

        Payment pay1 = new Payment(null, LocalDateTime.ofInstant(Instant.parse("2019-06-21T13:00:00Z"), ZoneOffset.UTC), o1);
        o1.setPayment(pay1);
        orderRepository.saveAndFlush(o1);

    }
}
