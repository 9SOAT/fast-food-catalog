package com.fiap.challenge.fast_food_catalog;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@AutoConfiguration
@ActiveProfiles("test")
class ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void example(@Autowired final MongoTemplate mongoTemplate) {
        Assertions.assertThat(mongoTemplate.getDb()).isNotNull();
    }

}
