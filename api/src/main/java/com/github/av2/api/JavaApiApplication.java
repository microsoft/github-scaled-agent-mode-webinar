package com.github.av2.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@SpringBootApplication
@RestController
public class JavaApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaApiApplication.class, args);
    }

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of("message", "Hello, world!");
    }
}