package com.github.av2.api.service;

import com.github.av2.api.model.Supplier;
import com.github.av2.api.data.SeedData;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    private final List<Supplier> suppliers = new ArrayList<>();
    private final SeedData seedData;

    @Autowired
    public SupplierService(SeedData seedData) {
        this.seedData = seedData;
        this.suppliers.addAll(seedData.getSuppliers());
    }

    public List<Supplier> findAll() {
        return new ArrayList<>(suppliers);
    }

    public Optional<Supplier> findById(Integer id) {
        return suppliers.stream()
            .filter(s -> s.getSupplierId().equals(id))
            .findFirst();
    }

    public Supplier save(Supplier supplier) {
        suppliers.removeIf(s -> s.getSupplierId().equals(supplier.getSupplierId()));
        suppliers.add(supplier);
        return supplier;
    }

    public boolean deleteById(Integer id) {
        return suppliers.removeIf(s -> s.getSupplierId().equals(id));
    }
}