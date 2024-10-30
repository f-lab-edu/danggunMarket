package com.example.danggunmarket.cartProduct;

import com.example.danggunmarket.cart.CartEntity;
import com.example.danggunmarket.common.entity.BaseEntity;
import com.example.danggunmarket.product.ProductEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "cart_product")
public class CartProductEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private CartEntity cart;

    @Builder
    public CartProductEntity(ProductEntity product, CartEntity cart) {
        this.product = product;
        this.cart = cart;
    }
}
