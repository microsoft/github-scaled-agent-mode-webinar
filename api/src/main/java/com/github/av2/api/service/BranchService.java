package com.github.av2.api.service;

import com.github.av2.api.model.Branch;
import com.github.av2.api.data.SeedData;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BranchService {
    private final List<Branch> branches = new ArrayList<>();
    private final SeedData seedData;

    @Autowired
    public BranchService(SeedData seedData) {
        this.seedData = seedData;
        this.branches.addAll(seedData.getBranches());
    }

    public List<Branch> findAll() {
        return new ArrayList<>(branches);
    }

    public Optional<Branch> findById(Integer id) {
        return branches.stream()
            .filter(b -> b.getBranchId().equals(id))
            .findFirst();
    }

    public Branch save(Branch branch) {
        branches.removeIf(b -> b.getBranchId().equals(branch.getBranchId()));
        branches.add(branch);
        return branch;
    }

    public boolean deleteById(Integer id) {
        return branches.removeIf(b -> b.getBranchId().equals(id));
    }
}