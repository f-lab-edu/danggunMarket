package com.example.danggunmarket.cartProduct;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartProductRepository extends JpaRepository<CartProductEntity, Long> {
    Optional<CartProductEntity> findByCartCartIdAndProductProductIdAndIsDeletedIsFalse(long cartId, long productId);

    Optional<CartProductEntity> findByCartCartIdAndProductProductId(long cartId, long productId);
}
