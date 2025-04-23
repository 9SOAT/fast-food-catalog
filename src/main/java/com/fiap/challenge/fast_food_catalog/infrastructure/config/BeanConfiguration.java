package com.fiap.challenge.fast_food_catalog.infrastructure.config;

import com.fiap.challenge.fast_food_catalog.domain.*;
import com.fiap.challenge.fast_food_catalog.domain.ports.inbound.ProductService;
import com.fiap.challenge.fast_food_catalog.domain.ports.outbound.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new DomainProductService(productRepository);
    }

    @Bean
    public DomainCatalogService catalogService(ProductRepository productRepository) {
        return new DomainCatalogService(productRepository);
    }
}
