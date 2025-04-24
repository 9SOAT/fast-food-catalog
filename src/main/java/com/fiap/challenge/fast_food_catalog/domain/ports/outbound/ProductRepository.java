package com.fiap.challenge.fast_food_catalog.domain.ports.outbound;

import com.fiap.challenge.fast_food_catalog.domain.model.PageResult;
import com.fiap.challenge.fast_food_catalog.domain.model.product.Product;
import com.fiap.challenge.fast_food_catalog.domain.model.product.ProductCategory;
import com.fiap.challenge.fast_food_catalog.domain.model.product.ProductStatus;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    PageResult<Product> findAllByCategory(ProductCategory category, int page, int size);

    Optional<Product> findById(String id);

    Product save(Product product);

    PageResult<Product> findAllByStatus(List<ProductStatus> statuses, int page, int size);
}
