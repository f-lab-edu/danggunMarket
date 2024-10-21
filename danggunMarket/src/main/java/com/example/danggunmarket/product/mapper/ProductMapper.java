package com.example.danggunmarket.product.mapper;

import com.example.danggunmarket.product.ProductEntity;
import com.example.danggunmarket.product.dto.AddProductRequest;
import com.example.danggunmarket.product.dto.GetProductDetailResponse;
import com.example.danggunmarket.product.dto.GetProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "member", target = "seller", ignore = true)
    GetProductResponse toGetDto(ProductEntity product);

    @Mapping(source = "member", target = "seller", ignore = true)
    GetProductDetailResponse toGetDetailDto(ProductEntity product);

    ProductEntity toEntity(AddProductRequest productRequest);
}
