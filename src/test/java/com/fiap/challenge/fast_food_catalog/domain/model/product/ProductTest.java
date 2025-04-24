package com.fiap.challenge.fast_food_catalog.domain.model.product;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static com.fiap.challenge.fast_food_catalog.domain.model.product.ProductStatus.ACTIVE;
import static com.fiap.challenge.fast_food_catalog.domain.model.product.ProductStatus.INACTIVE;
import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    @Test
    void update_updatesProductDetails() {
        Product originalProduct = new Product("1L", "Original Name", "Original Description", Set.of("image1.jpg"), new BigDecimal("10.00"), ProductCategory.SANDWICH, ACTIVE);
        Product updatedProduct = new Product("1L", "Updated Name", "Updated Description", Set.of("image2.jpg"), new BigDecimal("20.00"), ProductCategory.DRINK, ACTIVE);

        originalProduct.update(updatedProduct);

        assertThat(originalProduct.getName()).isEqualTo("Updated Name");
        assertThat(originalProduct.getDescription()).isEqualTo("Updated Description");
        assertThat(originalProduct.getImages()).isEqualTo(Set.of("image2.jpg"));
        assertThat(originalProduct.getPrice()).isEqualTo(new BigDecimal("20.00"));
        assertThat(originalProduct.getCategory()).isEqualTo(ProductCategory.DRINK);
    }

    @Test
    void inactivate_setsStatusToInactive() {
        Product product = new Product("1L", "Name", "Description", Set.of("image1.jpg"), new BigDecimal("10.00"), ProductCategory.SANDWICH, ACTIVE);

        product.inactivate();

        assertThat(product.getStatus()).isEqualTo(INACTIVE);
    }

    @Test
    void activate_setsStatusToActive() {
        Product product = new Product("1L", "Name", "Description", Set.of("image1.jpg"), new BigDecimal("10.00"), ProductCategory.SANDWICH, INACTIVE);

        product.activate();

        assertThat(product.getStatus()).isEqualTo(ACTIVE);
    }

    @Test
    void update_withNullValues_doesNotChangeFields() {
        Product originalProduct = new Product("1L", "Original Name", "Original Description", Set.of("image1.jpg"), new BigDecimal("10.00"), ProductCategory.SANDWICH, ACTIVE);
        Product updatedProduct = new Product("1L", null, null, null, null, null, ACTIVE);

        originalProduct.update(updatedProduct);

        assertThat(originalProduct.getName()).isNull();
        assertThat(originalProduct.getDescription()).isNull();
        assertThat(originalProduct.getImages()).isNull();
        assertThat(originalProduct.getPrice()).isNull();
        assertThat(originalProduct.getCategory()).isNull();
    }

}
