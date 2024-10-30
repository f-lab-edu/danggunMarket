package com.example.danggunmarket.cart.dto;

import com.example.danggunmarket.cartProduct.dto.GetProductInCartResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class GetCartResponse {
    private List<GetProductInCartResponse> result;
    private int totalPrice;
    private long cartId;
}
