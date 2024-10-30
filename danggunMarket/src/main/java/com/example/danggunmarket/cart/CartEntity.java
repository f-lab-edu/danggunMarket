package com.example.danggunmarket.cart;

import com.example.danggunmarket.cartProduct.CartProductEntity;
import com.example.danggunmarket.common.entity.BaseEntity;
import com.example.danggunmarket.member.MemberEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "cart")
public class CartEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;

    @OneToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
    private List<CartProductEntity> cartProducts;

    private int totalPrice;

    public CartEntity(MemberEntity member, int totalPrice) {
        this.member = member;
        this.totalPrice = totalPrice;
    }

    public void updateTotalPrice(int price){
        this.totalPrice += price;
    }
}
