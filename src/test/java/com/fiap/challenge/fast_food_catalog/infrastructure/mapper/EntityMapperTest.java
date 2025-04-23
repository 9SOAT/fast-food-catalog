package com.fiap.challenge.fast_food_catalog.infrastructure.mapper;

import com.fiap.challenge.fast_food_catalog.domain.model.product.Product;
import com.fiap.challenge.fast_food_catalog.domain.model.product.ProductCategory;
import com.fiap.challenge.fast_food_catalog.domain.model.product.ProductStatus;
import com.fiap.challenge.fast_food_catalog.fixture.ProductFixture;
import com.fiap.challenge.fast_food_catalog.infrastructure.entity.ProductCategoryEntity;
import com.fiap.challenge.fast_food_catalog.infrastructure.entity.ProductEntity;
import com.fiap.challenge.fast_food_catalog.infrastructure.entity.ProductStatusEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EntityMapperTest {
    EntityMapper entityMapper = EntityMapper.INSTANCE;

    @Test
    void shouldReturnNullWhenProductEntityIsNull() {
        assertNull(entityMapper.toProduct(null));
    }

    @Test
    void shouldMapProductEntityToProductCorrectly() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId("1");
        productEntity.setName("Burger");
        productEntity.setDescription("Delicious burger");
        productEntity.setImages(Set.of("image1.jpg", "image2.jpg"));
        productEntity.setPrice(BigDecimal.valueOf(9.99));
        productEntity.setCategory(ProductCategoryEntity.SANDWICH);
        productEntity.setStatus(ProductStatusEntity.ACTIVE);

        Product product = entityMapper.toProduct(productEntity);

        assertNotNull(product);
        assertEquals("1", product.getId());
        assertEquals("Burger", product.getName());
        assertEquals("Delicious burger", product.getDescription());
        assertEquals(Set.of("image1.jpg", "image2.jpg"), product.getImages());
        assertEquals(BigDecimal.valueOf(9.99), product.getPrice());
        assertEquals(ProductCategory.SANDWICH, product.getCategory());
        assertEquals(ProductStatus.ACTIVE, product.getStatus());
    }

    @Test
    void shouldReturnNullWhenProductIsNull() {
        assertThat(entityMapper.toProduct(null)).isNull();
    }


    @Test
    void shouldMapProductToProductEntity() {
        Product product = ProductFixture.aProduct();

        ProductEntity productEntity = entityMapper.toProductEntity(product);

        assertNotNull(productEntity);
        assertEquals(product.getId(), productEntity.getId());
        assertEquals(product.getName(), productEntity.getName());
        assertEquals(product.getDescription(), productEntity.getDescription());
        assertEquals(product.getImages(), productEntity.getImages());
        assertEquals(product.getPrice(), productEntity.getPrice());
        assertEquals(ProductCategoryEntity.SANDWICH, productEntity.getCategory());
        assertEquals(ProductStatusEntity.ACTIVE, productEntity.getStatus());
    }

    @Test
    void shouldHandleEmptyImagesSetInProductEntity() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setImages(Set.of());

        Product product = entityMapper.toProduct(productEntity);

        assertNotNull(product);
        assertTrue(product.getImages().isEmpty());
    }

    @Test
    void shouldHandleNullCategoryAndStatusInProductEntity() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory(null);
        productEntity.setStatus(null);

        Product product = entityMapper.toProduct(productEntity);

        assertNotNull(product);
        assertNull(product.getCategory());
        assertNull(product.getStatus());
    }

    @Test
    void shouldHandleNullPriceInProductEntity() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setPrice(null);

        Product product = entityMapper.toProduct(productEntity);

        assertNotNull(product);
        assertNull(product.getPrice());
    }

    @Test
    void shouldHandleNullImagesSetInProductEntity() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setImages(null);

        Product product = entityMapper.toProduct(productEntity);

        assertNotNull(product);
        assertNull(product.getImages());
    }

    @Test
    void shouldReturnNullWhenParameterIsNull() {
        assertThat(entityMapper.toStatusEntity(null)).isNull();
    }

    @Test
    void shouldReturnNullCatagoryWhenParameterIsNull() {
        assertThat(entityMapper.toCategoryEntity(null)).isNull();
    }

    @Test
    void shouldHandleNullFieldsInProductEntity() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(null);
        productEntity.setName(null);
        productEntity.setDescription(null);

        Product product = entityMapper.toProduct(productEntity);

        assertNotNull(product);
        assertNull(product.getId());
        assertNull(product.getName());
        assertNull(product.getDescription());
    }

    @ParameterizedTest
    @MethodSource("categoryEnumArguments")
    void shouldMapCategoryToEntityCorrectly(ProductCategory category, ProductCategoryEntity categoryEntity) {
        assertThat(entityMapper.toCategoryEntity(category)).isEqualTo(categoryEntity);
    }

    @ParameterizedTest
    @MethodSource("categoryEnumArguments")
    void shouldMapEntityToCategoryCorrectly(ProductCategory category, ProductCategoryEntity categoryEntity) {
        assertThat(entityMapper.toProductCategory(categoryEntity)).isEqualTo(category);
    }

    private static Stream<Arguments> categoryEnumArguments() {
        return Stream.of(
                Arguments.of(ProductCategory.SANDWICH, ProductCategoryEntity.SANDWICH),
                Arguments.of(ProductCategory.DRINK, ProductCategoryEntity.DRINK),
                Arguments.of(ProductCategory.SIDE_DISH, ProductCategoryEntity.SIDE_DISH),
                Arguments.of(ProductCategory.DESSERT, ProductCategoryEntity.DESSERT)
        );
    }


    @ParameterizedTest
    @MethodSource("statusEnumArguments")
    void shouldMapStatusToEntityCorrectly(ProductStatus status, ProductStatusEntity statusEntity) {
        assertThat(entityMapper.toStatusEntity(status)).isEqualTo(statusEntity);
    }

    @ParameterizedTest
    @MethodSource("statusEnumArguments")
    void shouldMapEntityToStatusCorrectly(ProductStatus status, ProductStatusEntity statusEntity) {
        assertThat(entityMapper.toProductStatus(statusEntity)).isEqualTo(status);
    }

    private static Stream<Arguments> statusEnumArguments() {
        return Stream.of(
                Arguments.of(ProductStatus.INACTIVE, ProductStatusEntity.INACTIVE),
                Arguments.of(ProductStatus.ACTIVE, ProductStatusEntity.ACTIVE)
        );
    }
}