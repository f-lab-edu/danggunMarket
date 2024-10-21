package com.example.danggunmarket.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class GetProductDetailResponse {
    private String name;
    private String seller;
    private String description;
    private long id;
    private int price;
    private String picturePath;
}
