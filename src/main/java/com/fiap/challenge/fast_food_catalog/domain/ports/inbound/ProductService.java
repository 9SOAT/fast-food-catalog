package com.fiap.challenge.fast_food_catalog.domain.ports.inbound;

import com.fiap.challenge.fast_food_catalog.domain.model.PageResult;
import com.fiap.challenge.fast_food_catalog.domain.model.product.Product;
import com.fiap.challenge.fast_food_catalog.domain.model.product.ProductStatus;

import java.util.List;

public interface ProductService {

    Product create(Product product);

    PageResult<Product> findAllByStatus(List<ProductStatus> statuses, int page, int size);

    Product findById(String id);

    Product update(String id, Product product);

    void delete(String id);
}
