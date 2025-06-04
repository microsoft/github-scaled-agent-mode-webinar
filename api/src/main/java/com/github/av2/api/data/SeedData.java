package com.github.av2.api.data;

import com.github.av2.api.model.*;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import java.util.*;

@Component
public class SeedData {
    private final List<Supplier> suppliers = new ArrayList<>();
    private final List<Product> products = new ArrayList<>();
    private final List<Headquarters> headquarters = new ArrayList<>();
    private final List<Branch> branches = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();
    private final List<OrderDetail> orderDetails = new ArrayList<>();
    private final List<Delivery> deliveries = new ArrayList<>();
    private final List<OrderDetailDelivery> orderDetailDeliveries = new ArrayList<>();

    @PostConstruct
    public void init() {
        initSuppliers();
        initProducts();
        initHeadquarters();
        initBranches();
        initOrders();
        initOrderDetails();
        initDeliveries();
        initOrderDetailDeliveries();
    }

    private void initSuppliers() {
        suppliers.add(new Supplier(1, "CircuitCore Labs", "Leading technology supplier", 
            "John Smith", "john@circuitcorelabs.co", "555-0101"));
        suppliers.add(new Supplier(2, "ConnectSphere", "Advanced tech products supplier", 
            "Jane Doe", "jane@connectsphere.com", "555-0102"));
    }

    private void initProducts() {
        products.add(new Product(1, 1, "PowerTool Pro X1", "High-performance powertool",
            1299.99f, "PWR-001", "piece", "powertool.png"));
        products.add(new Product(2, 1, "Webcam Pro", "Ergonomic webcam",
            49.99f, "WEB-001", "piece", "webcam.png"));
        products.add(new Product(3, 2, "Rugged Hard Drive", "Advanced storage solution",
            99.99f, "HDD-001", "piece", "harddrive.png"));
        products.add(new Product(4, 2, "Mic Pro", "Advanced audio mic",
            29.99f, "MIC-001", "piece", "mic.png"));
    }

    private void initHeadquarters() {
        headquarters.add(new Headquarters(1, "Main Office", "Corporate headquarters",
            "123 Main St, Business District", "Michael Johnson", "mjohnson@octo.com", "555-0001"));
    }

    private void initBranches() {
        branches.add(new Branch(1, 1, "Downtown Branch", "Main downtown location",
            "456 Market St", "Sarah Wilson", "swilson@octo.com", "555-0201"));
        branches.add(new Branch(2, 1, "Westside Branch", "Western district branch",
            "789 West Ave", "Robert Brown", "rbrown@octo.com", "555-0202"));
    }

    private void initOrders() {
        String now = new Date().toString();
        orders.add(new Order(1, 1, now, "pending", "Quarterly technology refresh"));
        orders.add(new Order(2, 2, now, "processing", "Monthly office supplies restock"));
    }

    private void initOrderDetails() {
        orderDetails.add(new OrderDetail(1, 1, 2, 5, 49.99f, "Webcams for new hires"));
        orderDetails.add(new OrderDetail(2, 1, 3, 5, 99.99f, "Harddrives for new field agents"));
        orderDetails.add(new OrderDetail(3, 2, 4, 20, 29.99f, "Mics for conference rooms"));
    }

    private void initDeliveries() {
        String date1 = new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000).toString();
        String date2 = new Date(System.currentTimeMillis() + 2 * 24 * 60 * 60 * 1000).toString();
        
        deliveries.add(new Delivery(1, 1, "pending", date1, "Delivery of tech equipment"));
        deliveries.add(new Delivery(2, 2, "in-transit", date2, "Standard delivery"));
    }

    private void initOrderDetailDeliveries() {
        String now = new Date().toString();
        orderDetailDeliveries.add(new OrderDetailDelivery(1, 1, 5, "pending", now, "Delivery batch"));
        orderDetailDeliveries.add(new OrderDetailDelivery(2, 2, 5, "pending", now, "Delivery batch"));
        orderDetailDeliveries.add(new OrderDetailDelivery(3, 3, 20, "in-transit", now, "Delivery"));
    }

    // Getters for the collections
    public List<Supplier> getSuppliers() { return new ArrayList<>(suppliers); }
    public List<Product> getProducts() { return new ArrayList<>(products); }
    public List<Headquarters> getHeadquarters() { return new ArrayList<>(headquarters); }
    public List<Branch> getBranches() { return new ArrayList<>(branches); }
    public List<Order> getOrders() { return new ArrayList<>(orders); }
    public List<OrderDetail> getOrderDetails() { return new ArrayList<>(orderDetails); }
    public List<Delivery> getDeliveries() { return new ArrayList<>(deliveries); }
    public List<OrderDetailDelivery> getOrderDetailDeliveries() { return new ArrayList<>(orderDetailDeliveries); }
}