package com.fiap.challenge.fast_food_catalog.infrastructure.mapper;

import com.fiap.challenge.fast_food_catalog.application.request.ProductMutation;
import com.fiap.challenge.fast_food_catalog.application.response.ProductCategoryView;
import com.fiap.challenge.fast_food_catalog.application.response.ProductView;
import com.fiap.challenge.fast_food_catalog.domain.model.product.Product;
import com.fiap.challenge.fast_food_catalog.domain.model.product.ProductCategory;
import com.fiap.challenge.fast_food_catalog.domain.model.product.ProductStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ViewMapperImplTest {

    ViewMapper viewMapper = ViewMapper.INSTANCE;

    @Test
        void shouldReturnNullWhenProductIsNull() {
            assertNull(viewMapper.toProductView(null));
        }

        @Test
        void shouldMapProductToProductViewCorrectly() {
            Product product = new Product("1", "Pizza", "Cheesy pizza", Set.of("image1.jpg"), BigDecimal.valueOf(15.99), ProductCategory.SANDWICH, ProductStatus.ACTIVE);

            ProductView productView = viewMapper.toProductView(product);

            assertNotNull(productView);
            assertEquals("1", productView.id());
            assertEquals("Pizza", productView.name());
            assertEquals("Cheesy pizza", productView.description());
            assertEquals(Set.of("image1.jpg"), productView.images());
            assertEquals(BigDecimal.valueOf(15.99), productView.price());
            assertEquals(ProductCategoryView.SANDWICH, productView.category());
            assertEquals(ProductStatus.ACTIVE, productView.status());
        }

        @Test
        void shouldHandleNullImagesInProduct() {
            Product product = new Product("1", "Pizza", "Cheesy pizza", null, BigDecimal.valueOf(15.99), ProductCategory.SANDWICH, ProductStatus.ACTIVE);

            ProductView productView = viewMapper.toProductView(product);

            assertNotNull(productView);
            assertNull(productView.images());
        }

        @Test
        void shouldReturnNullWhenProductMutationIsNull() {
            assertNull(viewMapper.toProduct(null));
        }

        @Test
        void shouldMapProductMutationToProductCorrectly() {
            ProductMutation productMutation = new ProductMutation("Burger", "Tasty burger", Set.of("image1.jpg", "image2.jpg"), BigDecimal.valueOf(10.99), ProductCategoryView.SANDWICH);

            Product product = viewMapper.toProduct(productMutation);

            assertNotNull(product);
            assertNull(product.getId());
            assertEquals("Burger", product.getName());
            assertEquals("Tasty burger", product.getDescription());
            assertEquals(Set.of("image1.jpg", "image2.jpg"), product.getImages());
            assertEquals(BigDecimal.valueOf(10.99), product.getPrice());
            assertEquals(ProductCategory.SANDWICH, product.getCategory());
            assertNull(product.getStatus());
        }

        @Test
        void shouldHandleNullImagesInProductMutation() {
            ProductMutation productMutation = new ProductMutation("Burger", "Tasty burger", null, BigDecimal.valueOf(10.99), ProductCategoryView.SANDWICH);

            Product product = viewMapper.toProduct(productMutation);

            assertNotNull(product);
            assertNull(product.getImages());
        }
}