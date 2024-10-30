package com.example.danggunmarket.cartProduct.dto;


import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class GetProductInCartResponse {
    private String name;
    private long productId;
    private int price;
    private String picturePath;
}
