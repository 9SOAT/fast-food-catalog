package com.fiap.challenge.fast_food_catalog.infrastructure.repository;

import com.fiap.challenge.fast_food_catalog.domain.model.PageResult;
import com.fiap.challenge.fast_food_catalog.domain.model.product.Product;
import com.fiap.challenge.fast_food_catalog.domain.model.product.ProductCategory;
import com.fiap.challenge.fast_food_catalog.domain.model.product.ProductStatus;
import com.fiap.challenge.fast_food_catalog.fixture.ProductEntityFixture;
import com.fiap.challenge.fast_food_catalog.fixture.ProductFixture;
import com.fiap.challenge.fast_food_catalog.infrastructure.entity.ProductCategoryEntity;
import com.fiap.challenge.fast_food_catalog.infrastructure.entity.ProductStatusEntity;
import com.fiap.challenge.fast_food_catalog.infrastructure.mapper.EntityMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static com.fiap.challenge.fast_food_catalog.infrastructure.repository.PostgresProductRepository.SORT_BY_NAME_ASC;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostgresProductRepositoryTest {

    @Mock
    private JpaProductRepository jpaProductRepositoryMock;

    @Mock
    private EntityMapper entityMapperMock;

    @InjectMocks
    private PostgresProductRepository target;

    @Test
    void findAllByStatusByCategory_whenCategoryExists_shouldReturnPageOfProducts() {
        ProductCategoryEntity categoryEntity = ProductCategoryEntity.SANDWICH;
        ProductCategory category = ProductCategory.SANDWICH;
        int page = 0;
        int size = 10;

        when(entityMapperMock.toCategoryEntity(category))
            .thenReturn(categoryEntity);

        when(jpaProductRepositoryMock.findAllByCategoryAndStatus(categoryEntity, ProductStatusEntity.ACTIVE,
            PageRequest.of(page, size, SORT_BY_NAME_ASC)))
            .thenReturn(new PageImpl<>(List.of(ProductEntityFixture.aValidProductEntityWithId()), PageRequest.of(page, size), 1));

        Product product = ProductFixture.aProduct();
        when(entityMapperMock.toProduct(ProductEntityFixture.aValidProductEntityWithId()))
            .thenReturn(product);

        PageResult<Product> allByCategory = target.findAllByCategory(category, page, size);

        assertEquals(1, allByCategory.getTotalElements());
        assertEquals(1, allByCategory.getTotalPages());
        assertEquals(1, allByCategory.getContent().size());
        assertEquals(product, allByCategory.getContent().get(0));
    }

    @Test
    void findAllByStatusByCategory_whenCategoryDoesNotExist_shouldReturnEmptyPage() {
        ProductCategoryEntity categoryEntity = ProductCategoryEntity.SANDWICH;
        ProductCategory category = ProductCategory.SANDWICH;
        int page = 0;
        int size = 10;

        when(entityMapperMock.toCategoryEntity(category))
            .thenReturn(categoryEntity);

        when(jpaProductRepositoryMock.findAllByCategoryAndStatus(categoryEntity, ProductStatusEntity.ACTIVE,
            PageRequest.of(page, size, SORT_BY_NAME_ASC)))
            .thenReturn(new PageImpl<>(List.of(), PageRequest.of(page, size), 0));

        PageResult<Product> allByCategory = target.findAllByCategory(category, page, size);

        assertEquals(0, allByCategory.getTotalElements());
        assertEquals(0, allByCategory.getTotalPages());
        assertEquals(0, allByCategory.getContent().size());
    }

    @Test
    void findById_whenProductExists_shouldReturnProduct() {
        String id = "1L";
        Product product = ProductFixture.aProduct();
        when(jpaProductRepositoryMock.findById(id))
            .thenReturn(Optional.of(ProductEntityFixture.aValidProductEntityWithId()));

        when(entityMapperMock.toProduct(ProductEntityFixture.aValidProductEntityWithId()))
            .thenReturn(product);

        Optional<Product> found = target.findById(id);

        assertEquals(product, found.get());
    }

    @Test
    void save_whenProductIsValid_shouldReturnProduct() {
        Product product = ProductFixture.aProduct();
        when(jpaProductRepositoryMock.save(any()))
            .thenReturn(ProductEntityFixture.aValidProductEntityWithId());

        when(entityMapperMock.toProduct(ProductEntityFixture.aValidProductEntityWithId()))
            .thenReturn(product);

        Product saved = target.save(product);

        assertEquals(product, saved);
    }

    @Test
    void findAll_ByStatus_whenProductsExists_shouldReturnPageOfProducts() {
        int page = 0;
        int size = 10;
        when(jpaProductRepositoryMock.findAllByStatusIn(any(), any()))
            .thenReturn(new PageImpl<>(List.of(ProductEntityFixture.aValidProductEntityWithId()), PageRequest.of(page, size), 1));

        Product product = ProductFixture.aProduct();
        when(entityMapperMock.toProduct(ProductEntityFixture.aValidProductEntityWithId()))
            .thenReturn(product);

        PageResult<Product> allByCategory = target.findAllByStatus(List.of(ProductStatus.ACTIVE), page, size);

        assertEquals(1, allByCategory.getTotalElements());
        assertEquals(1, allByCategory.getTotalPages());
        assertEquals(1, allByCategory.getContent().size());
        assertEquals(product, allByCategory.getContent().get(0));
    }

    @Test
    void findAll_ByStatus_whenProductsDoesNotExist_shouldReturnEmptyPage() {
        int page = 0;
        int size = 10;
        when(jpaProductRepositoryMock.findAllByStatusIn(any(), any()))
            .thenReturn(new PageImpl<>(List.of(), PageRequest.of(page, size), 0));

        PageResult<Product> allByCategory = target.findAllByStatus(List.of(ProductStatus.ACTIVE), page, size);

        assertEquals(0, allByCategory.getTotalElements());
        assertEquals(0, allByCategory.getTotalPages());
        assertEquals(0, allByCategory.getContent().size());
    }
}
