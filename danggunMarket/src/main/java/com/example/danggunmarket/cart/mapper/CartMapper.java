package com.example.danggunmarket.cart.mapper;

import com.example.danggunmarket.cart.CartEntity;
import com.example.danggunmarket.cart.dto.GetCartResponse;
import com.example.danggunmarket.cartProduct.dto.GetProductInCartResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    @Mapping(source = "cart.cartId", target = "cartId")
    @Mapping(source = "cartProduct", target= "result")
    @Mapping(source = "cart.totalPrice", target = "totalPrice")
    GetCartResponse toGetCarDTO(CartEntity cart, List<GetProductInCartResponse> cartProduct);
}
