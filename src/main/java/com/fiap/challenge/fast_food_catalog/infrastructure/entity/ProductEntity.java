package com.fiap.challenge.fast_food_catalog.infrastructure.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "product")
@CompoundIndex(name = "idx_product_category_status", def = "{'category': 1, 'status': 1}")
public class ProductEntity {

    @Id
    private String id;

    @NotNull
    @Indexed(name = "uk_product_name", unique = true)
    private String name;

    private String description;

    @NotEmpty
    private Set<String> images;

    @NotNull
    private BigDecimal price;

    @NotNull
    private ProductCategoryEntity category;

    @NotNull
    @Indexed(name = "idx_product_status")
    private ProductStatusEntity status;
}
