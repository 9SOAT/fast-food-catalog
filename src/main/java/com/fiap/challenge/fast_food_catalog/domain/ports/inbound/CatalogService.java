package com.fiap.challenge.fast_food_catalog.domain.ports.inbound;

import com.fiap.challenge.fast_food_catalog.domain.model.PageResult;
import com.fiap.challenge.fast_food_catalog.domain.model.product.Product;
import com.fiap.challenge.fast_food_catalog.domain.model.product.ProductCategory;

public interface CatalogService {

    PageResult<Product> findAllByCategory(ProductCategory category, int page, int size);

    Product getById(String id);

}
