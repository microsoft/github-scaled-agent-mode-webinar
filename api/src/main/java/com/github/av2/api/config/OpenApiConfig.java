package com.github.av2.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("OctoCAT Supply Chain Management API")
                        .description("REST API for managing the OctoCAT supply chain")
                        .version("1.0.0"));
    }
}