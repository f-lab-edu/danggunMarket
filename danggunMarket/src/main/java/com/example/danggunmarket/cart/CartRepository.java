package com.example.danggunmarket.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
    @Query("SELECT c FROM CartEntity c " +
            "JOIN FETCH c.cartProducts cp " +
            "JOIN FETCH cp.product p " +
            "WHERE c.member.memberId = :id " +
            "AND cp.isDeleted = false")
    Optional<CartEntity> findByMemberMemberIdAndIsDeletedIsFalse(@Param("id") long memberId);

}
