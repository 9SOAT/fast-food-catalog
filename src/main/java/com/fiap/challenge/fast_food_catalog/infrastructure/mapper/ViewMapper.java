package com.fiap.challenge.fast_food_catalog.infrastructure.mapper;

import com.fiap.challenge.fast_food_catalog.application.request.ProductMutation;
import com.fiap.challenge.fast_food_catalog.application.response.ProductView;
import com.fiap.challenge.fast_food_catalog.domain.model.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ViewMapper {

    ViewMapper INSTANCE = Mappers.getMapper(ViewMapper.class);

    ProductView toProductView(Product product);

    Product toProduct(ProductMutation productMutation);

}
