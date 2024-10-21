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
    private long id;
    private int price;
    private String picturePath;
}
