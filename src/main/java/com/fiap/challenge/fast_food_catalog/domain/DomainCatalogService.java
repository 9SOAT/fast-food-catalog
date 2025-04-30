package com.fiap.challenge.fast_food_catalog.domain;

import com.fiap.challenge.fast_food_catalog.domain.model.PageResult;
import com.fiap.challenge.fast_food_catalog.domain.model.exception.NotFoundException;
import com.fiap.challenge.fast_food_catalog.domain.model.product.Product;
import com.fiap.challenge.fast_food_catalog.domain.model.product.ProductCategory;
import com.fiap.challenge.fast_food_catalog.domain.ports.inbound.CatalogService;
import com.fiap.challenge.fast_food_catalog.domain.ports.outbound.ProductRepository;

public class DomainCatalogService implements CatalogService {

    private final ProductRepository productRepository;

    public DomainCatalogService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public PageResult<Product> findAllByCategory(ProductCategory category, int page, int size) {
        return productRepository.findAllByCategory(category, page, size);
    }

    public Product getById(String id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(String.format("Product not found id=%s", id), "PRODUCT_NOT_FOUND"));
    }

    public Product findById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Product not found id=%s", id), "PRODUCT_NOT_FOUND"));
    }
}
