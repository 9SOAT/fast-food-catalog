package com.fiap.challenge.fast_food_catalog.domain;

import com.fiap.challenge.fast_food_catalog.domain.model.PageResult;
import com.fiap.challenge.fast_food_catalog.domain.model.exception.NotFoundException;
import com.fiap.challenge.fast_food_catalog.domain.model.product.Product;
import com.fiap.challenge.fast_food_catalog.domain.model.product.ProductStatus;
import com.fiap.challenge.fast_food_catalog.domain.ports.inbound.ProductService;
import com.fiap.challenge.fast_food_catalog.domain.ports.outbound.ProductRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class DomainProductService implements ProductService {

    private final ProductRepository productRepository;

    public DomainProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        log.info("Creating product {}", product);
        product.activate();
        return productRepository.save(product);
    }

    @Override
    public PageResult<Product> findAllByStatus(List<ProductStatus> statuses, int page, int size) {
        return productRepository.findAllByStatus(statuses, page, size);
    }

    @Override
    public Product findById(String id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(String.format("Product not found %s", id), "PRODUCT_NOT_FOUND"));
    }

    @Override
    public Product update(String id, Product product) {
        log.info("Updating product id={} {}", id, product);
        Product dbProduct = findById(id);
        dbProduct.update(product);
        return productRepository.save(dbProduct);
    }

    @Override
    public void delete(String id) {
        log.info("Deleting product id={}", id);
        Product product = findById(id);
        product.inactivate();
        productRepository.save(product);
    }
}
