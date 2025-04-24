package com.fiap.challenge.fast_food_catalog.fixture;

import com.fiap.challenge.fast_food_catalog.domain.model.product.Product;
import com.fiap.challenge.fast_food_catalog.domain.model.product.ProductCategory;
import com.fiap.challenge.fast_food_catalog.domain.model.product.ProductStatus;

import java.math.BigDecimal;
import java.util.Set;

public class ProductFixture {

    public static Product aProduct() {
        return new Product("1L", "Product 1", "Description 1", Set.of("image1.jpg"), BigDecimal.TEN, ProductCategory.SANDWICH, ProductStatus.ACTIVE);
    }
}
