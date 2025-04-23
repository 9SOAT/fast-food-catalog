package com.fiap.challenge.fast_food_catalog.application.request;

import com.fiap.challenge.fast_food_catalog.application.response.ProductCategoryView;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Set;

public record ProductMutation(
    @NotNull String name,
    @NotNull String description,
    @NotEmpty Set<String> images,
    @NotNull BigDecimal price,
    @NotNull ProductCategoryView category
) {
}
