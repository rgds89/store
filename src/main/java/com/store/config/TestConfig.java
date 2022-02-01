package com.store.config;

import com.store.entities.Order;
import com.store.entities.User;
import com.store.entities.enums.OrderStatus;
import com.store.repositories.OrderRepository;
import com.store.repositories.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

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


    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        userRespository.saveAll(Arrays.asList(u1, u2));

        Order o1 = new Order(null, LocalDateTime.ofInstant(Instant.parse("2019-06-20T19:53:07Z"), ZoneOffset.UTC), OrderStatus.PAID, u1);
        Order o2 = new Order(null,  LocalDateTime.ofInstant(Instant.parse("2019-07-21T03:42:10Z"), ZoneOffset.UTC), OrderStatus.WAITING_PAYMENT, u2);
        Order o3 = new Order(null,  LocalDateTime.ofInstant(Instant.parse("2019-07-22T15:21:22Z"), ZoneOffset.UTC), OrderStatus.WAITING_PAYMENT, u1);

        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
    }
}
