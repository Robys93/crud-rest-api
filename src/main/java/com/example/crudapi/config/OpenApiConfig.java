package com.example.crudapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger / OpenAPI metadata. SpringDoc exposes the UI at
 * /swagger-ui/index.html and the spec at /v3/api-docs.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI().info(new Info()
                .title("CRUD REST API")
                .version("1.0.0")
                .description("REST API CRUD su User e Product con layer generico riusabile (T, DTO, ID)."));
    }
}
