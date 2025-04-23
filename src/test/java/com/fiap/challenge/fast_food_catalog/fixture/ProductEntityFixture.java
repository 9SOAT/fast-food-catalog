package com.fiap.challenge.fast_food_catalog.fixture;

import com.fiap.challenge.fast_food_catalog.infrastructure.entity.ProductCategoryEntity;
import com.fiap.challenge.fast_food_catalog.infrastructure.entity.ProductEntity;
import com.fiap.challenge.fast_food_catalog.infrastructure.entity.ProductStatusEntity;

import java.math.BigDecimal;
import java.util.Set;


public class ProductEntityFixture {

    public static ProductEntity aValidProductEntity() {
        return new ProductEntity(null, "Pizza de calabresa", "massa tradicional, molho de tomate artezanal, calabresa fatiada e cebola", Set.of(""), new BigDecimal(10), ProductCategoryEntity.SANDWICH, ProductStatusEntity.ACTIVE);
    }

    public static ProductEntity aValidProductEntityWithId() {
        return new ProductEntity("1L", "Pizza de calabresa", "massa tradicional, molho de tomate artezanal, calabresa fatiada e cebola", Set.of(""), new BigDecimal(10), ProductCategoryEntity.SANDWICH, ProductStatusEntity.ACTIVE);
    }
}
