package com.fiap.challenge.fast_food_catalog.infrastructure.mapper;

import com.fiap.challenge.fast_food_catalog.domain.model.product.Product;
import com.fiap.challenge.fast_food_catalog.domain.model.product.ProductCategory;
import com.fiap.challenge.fast_food_catalog.domain.model.product.ProductStatus;
import com.fiap.challenge.fast_food_catalog.infrastructure.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    Product toProduct(ProductEntity productEntity);

    ProductEntity toProductEntity(Product product);

    ProductCategoryEntity toCategoryEntity(ProductCategory category);

    ProductCategory toProductCategory(ProductCategoryEntity productCategoryEntity);

    ProductStatusEntity toStatusEntity(ProductStatus productStatus);

    ProductStatus toProductStatus(ProductStatusEntity productStatusEntity);
}
