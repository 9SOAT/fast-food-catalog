package com.fiap.challenge.fast_food_catalog.infrastructure.repository;

import com.fiap.challenge.fast_food_catalog.infrastructure.entity.ProductCategoryEntity;
import com.fiap.challenge.fast_food_catalog.infrastructure.entity.ProductEntity;
import com.fiap.challenge.fast_food_catalog.infrastructure.entity.ProductStatusEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaProductRepository extends MongoRepository<ProductEntity, String> {

    Page<ProductEntity> findAllByCategoryAndStatus(ProductCategoryEntity category, ProductStatusEntity status, Pageable pageable);

    Page<ProductEntity> findAllByStatusIn(List<ProductStatusEntity> productStatusEntity, Pageable pageable);
}
