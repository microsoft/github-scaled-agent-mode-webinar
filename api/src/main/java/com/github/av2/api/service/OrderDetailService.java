package com.github.av2.api.service;

import com.github.av2.api.model.OrderDetail;
import com.github.av2.api.data.SeedData;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {
    private final List<OrderDetail> orderDetails = new ArrayList<>();
    private final SeedData seedData;

    @Autowired
    public OrderDetailService(SeedData seedData) {
        this.seedData = seedData;
        this.orderDetails.addAll(seedData.getOrderDetails());
    }

    public List<OrderDetail> findAll() {
        return new ArrayList<>(orderDetails);
    }

    public Optional<OrderDetail> findById(Integer id) {
        return orderDetails.stream()
            .filter(od -> od.getOrderDetailId().equals(id))
            .findFirst();
    }

    public OrderDetail save(OrderDetail orderDetail) {
        orderDetails.removeIf(od -> od.getOrderDetailId().equals(orderDetail.getOrderDetailId()));
        orderDetails.add(orderDetail);
        return orderDetail;
    }

    public boolean deleteById(Integer id) {
        return orderDetails.removeIf(od -> od.getOrderDetailId().equals(id));
    }
}