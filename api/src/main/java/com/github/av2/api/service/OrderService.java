package com.github.av2.api.service;

import com.github.av2.api.model.Order;
import com.github.av2.api.data.SeedData;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final List<Order> orders = new ArrayList<>();
    private final SeedData seedData;

    @Autowired
    public OrderService(SeedData seedData) {
        this.seedData = seedData;
        this.orders.addAll(seedData.getOrders());
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders);
    }

    public Optional<Order> findById(Integer id) {
        return orders.stream()
            .filter(o -> o.getOrderId().equals(id))
            .findFirst();
    }

    public Order save(Order order) {
        orders.removeIf(o -> o.getOrderId().equals(order.getOrderId()));
        orders.add(order);
        return order;
    }

    public boolean deleteById(Integer id) {
        return orders.removeIf(o -> o.getOrderId().equals(id));
    }
}