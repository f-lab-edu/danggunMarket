package com.example.danggunmarket.cartProduct.mapper;

import com.example.danggunmarket.cart.CartEntity;
import com.example.danggunmarket.cartProduct.dto.AddProductToCartRequest;
import com.example.danggunmarket.cartProduct.CartProductEntity;
import com.example.danggunmarket.cartProduct.dto.GetProductInCartResponse;
import com.example.danggunmarket.product.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartProductMapper {
    CartProductMapper INSTANCE = Mappers.getMapper(CartProductMapper.class);

    @Mapping(source = "cart", target = "cart")
    @Mapping(source = "product", target = "product")
    CartProductEntity toEntity(AddProductToCartRequest request, CartEntity cart, ProductEntity product);

    @Mapping(source = "product.name", target = "name")
    @Mapping(source = "product.price", target = "price")
    @Mapping(source = "product.picturePath", target = "picturePath")
    @Mapping(source = "product.productId", target = "productId")
    GetProductInCartResponse toGetCartResponse(CartProductEntity cartProduct, long cartId, ProductEntity product);
}
