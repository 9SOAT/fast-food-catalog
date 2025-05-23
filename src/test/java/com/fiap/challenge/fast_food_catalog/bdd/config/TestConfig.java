package com.fiap.challenge.fast_food_catalog.bdd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import com.fiap.challenge.fast_food_catalog.bdd.steps.TestContext;

@Configuration
public class TestConfig {

    @Bean
    TestContext testContext(Environment environment){
        TestContext context = new TestContext();
        context.setEnv(environment);
        return context;
    }

}
