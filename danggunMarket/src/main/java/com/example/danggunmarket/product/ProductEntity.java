package com.example.danggunmarket.product;

import com.example.danggunmarket.common.entity.BaseEntity;
import com.example.danggunmarket.member.MemberEntity;
import com.example.danggunmarket.product.status.ProductStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@NoArgsConstructor
@Getter
public class ProductEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    private String name;
    private String description;
    private int price;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    private String picturePath;

    private LocalDateTime soldAt;

    public void setMember(MemberEntity member){
        this.member = member;
    }

    public void setPicturePath(String picturePath){
        this.picturePath = picturePath;
    }

    @Builder
    public ProductEntity(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = ProductStatus.SELLING;
    }

    public void update(String name, String description, int price){
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
