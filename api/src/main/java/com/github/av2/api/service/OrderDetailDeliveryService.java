package com.github.av2.api.service;

import com.github.av2.api.model.OrderDetailDelivery;
import com.github.av2.api.data.SeedData;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailDeliveryService {
    private final List<OrderDetailDelivery> orderDetailDeliveries = new ArrayList<>();

    private final SeedData seedData;

    @Autowired
    public OrderDetailDeliveryService(SeedData seedData) {
        this.seedData = seedData;
        this.orderDetailDeliveries.addAll(seedData.getOrderDetailDeliveries());
    }

    public List<OrderDetailDelivery> findAll() {
        return new ArrayList<>(orderDetailDeliveries);
    }

    public Optional<OrderDetailDelivery> findById(Integer id) {
        return orderDetailDeliveries.stream()
            .filter(odd -> odd.getDeliveryId().equals(id))
            .findFirst();
    }

    public OrderDetailDelivery save(OrderDetailDelivery orderDetailDelivery) {
        orderDetailDeliveries.removeIf(odd -> odd.getDeliveryId().equals(orderDetailDelivery.getDeliveryId()));
        orderDetailDeliveries.add(orderDetailDelivery);
        return orderDetailDelivery;
    }

    public boolean deleteById(Integer id) {
        return orderDetailDeliveries.removeIf(odd -> odd.getDeliveryId().equals(id));
    }
}