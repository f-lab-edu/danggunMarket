package com.example.danggunmarket.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EditProductRequest {
    private String name;
    private String description;
    private int price;
}
