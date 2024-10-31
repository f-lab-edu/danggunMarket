package com.example.danggunmarket.product.dto;


import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class GetProductResponse {
    private String name;
    private String seller;
    private long productId;
    private int price;
    private String picturePath;
}
