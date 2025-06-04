package com.github.av2.api.service;

import com.github.av2.api.model.Headquarters;
import com.github.av2.api.data.SeedData;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HeadquartersService {
    private final List<Headquarters> headquarters = new ArrayList<>();
    private final SeedData seedData;

    @Autowired
    public HeadquartersService(SeedData seedData) {
        this.seedData = seedData;
        this.headquarters.addAll(seedData.getHeadquarters());
    }

    public List<Headquarters> findAll() {
        return new ArrayList<>(headquarters);
    }

    public Optional<Headquarters> findById(Integer id) {
        return headquarters.stream()
            .filter(h -> h.getHeadquartersId().equals(id))
            .findFirst();
    }

    public Headquarters save(Headquarters headquarters) {
        this.headquarters.removeIf(h -> h.getHeadquartersId().equals(headquarters.getHeadquartersId()));
        this.headquarters.add(headquarters);
        return headquarters;
    }

    public boolean deleteById(Integer id) {
        return headquarters.removeIf(h -> h.getHeadquartersId().equals(id));
    }
}