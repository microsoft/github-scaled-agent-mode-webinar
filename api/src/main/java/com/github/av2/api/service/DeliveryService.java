package com.github.av2.api.service;

import com.github.av2.api.model.Delivery;
import com.github.av2.api.data.SeedData;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class DeliveryService {
    private final List<Delivery> deliveries = new ArrayList<>();

    private final SeedData seedData;

    @Autowired
    public DeliveryService(SeedData seedData) {
        this.seedData = seedData;
        this.deliveries.addAll(seedData.getDeliveries());
    }

    public List<Delivery> findAll() {
        return new ArrayList<>(deliveries);
    }

    public Optional<Delivery> findById(Integer id) {
        return deliveries.stream()
            .filter(d -> d.getDeliveryId().equals(id))
            .findFirst();
    }

    public Delivery save(Delivery delivery) {
        deliveries.removeIf(d -> d.getDeliveryId().equals(delivery.getDeliveryId()));
        deliveries.add(delivery);
        return delivery;
    }

    public boolean deleteById(Integer id) {
        return deliveries.removeIf(d -> d.getDeliveryId().equals(id));
    }

    public Map<String, Object> updateStatus(Integer id, String status, String notifyCommand) {
        Map<String, Object> response = new HashMap<>();
        
        Optional<Delivery> optionalDelivery = findById(id);
        if (optionalDelivery.isEmpty()) {
            return null;
        }

        Delivery delivery = optionalDelivery.get();
        delivery.setStatus(status);
        save(delivery);
        response.put("delivery", delivery);

        if (notifyCommand != null && !notifyCommand.isBlank()) {
            try {
                Process process = Runtime.getRuntime().exec(notifyCommand);
                process.waitFor(10, TimeUnit.SECONDS);
                
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
                );
                
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
                
                response.put("commandOutput", output.toString());
            } catch (Exception e) {
                throw new RuntimeException("Failed to execute notification command", e);
            }
        }

        return response;
    }
}