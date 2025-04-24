package com.fiap.challenge.fast_food_catalog.application.response;

import com.fiap.challenge.fast_food_catalog.domain.model.product.ProductStatus;

import java.math.BigDecimal;
import java.util.Set;

public record ProductView(
    String id,
    String name,
    String description,
    Set<String> images,
    BigDecimal price,
    ProductCategoryView category,
    ProductStatus status
) {
}
