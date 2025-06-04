package com.github.av2.api.service;

import com.github.av2.api.model.Product;
import com.github.av2.api.data.SeedData;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();
    private final SeedData seedData;

    @Autowired
    public ProductService(SeedData seedData) {
        this.seedData = seedData;
        this.products.addAll(seedData.getProducts());
    }

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public Optional<Product> findById(Integer id) {
        return products.stream()
            .filter(p -> p.getProductId().equals(id))
            .findFirst();
    }

    public Product save(Product product) {
        products.removeIf(p -> p.getProductId().equals(product.getProductId()));
        products.add(product);
        return product;
    }

    public boolean deleteById(Integer id) {
        return products.removeIf(p -> p.getProductId().equals(id));
    }
}