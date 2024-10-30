package com.example.danggunmarket.cart;

import com.example.danggunmarket.cart.dto.GetCartResponse;
import com.example.danggunmarket.cart.exception.CartErrorCode;
import com.example.danggunmarket.cart.exception.CartNotFoundException;
import com.example.danggunmarket.cart.mapper.CartMapper;
import com.example.danggunmarket.cartProduct.CartProductEntity;
import com.example.danggunmarket.cartProduct.CartProductRepository;
import com.example.danggunmarket.cartProduct.dto.AddProductToCartRequest;
import com.example.danggunmarket.cartProduct.dto.GetProductInCartResponse;
import com.example.danggunmarket.cartProduct.exception.CartHasNotThisProductException;
import com.example.danggunmarket.cartProduct.exception.CartProductErrorCode;
import com.example.danggunmarket.cartProduct.mapper.CartProductMapper;
import com.example.danggunmarket.common.auth.LoggedInMember;
import com.example.danggunmarket.member.MemberEntity;
import com.example.danggunmarket.member.MemberService;
import com.example.danggunmarket.product.ProductEntity;
import com.example.danggunmarket.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;
    private final ProductService productService;
    private final MemberService memberService;

    @Transactional(readOnly = true)
    public GetCartResponse getCart(long memberId) {
        CartEntity cart = findByMemberId(memberId);
        List<GetProductInCartResponse> products = cart.getCartProducts()
                .stream()
                .filter(cartProduct -> !cartProduct.isDeleted())
                .map(cartProduct -> CartProductMapper
                        .INSTANCE.
                        toGetCartResponse(cartProduct, cart.getCartId(), cartProduct.getProduct()))
                .toList();

        return CartMapper.INSTANCE.toGetCarDTO(cart, products);
    }

    @Transactional
    // /v1/cartProduct
    public void addProductToCart(AddProductToCartRequest addProductRequest, LoggedInMember loggedInMember) {
        CartEntity cart = findByMemberId(loggedInMember.getId());
        ProductEntity product = productService.findById(addProductRequest.getProductId());

        Optional<CartProductEntity> cartProductEntity = cartProductRepository
                .findByCartCartIdAndProductProductId(cart.getCartId(), addProductRequest.getProductId());

        if (cartProductEntity.isPresent()) {
            if (cartProductEntity.get().isDeleted()) {
                cart.updateTotalPrice(product.getPrice());
                cartProductEntity.get().setDeleted();
            }
            return;
        }

        CartProductEntity newCartProductEntity = CartProductMapper.INSTANCE.toEntity(addProductRequest, cart, product);
        cart.updateTotalPrice(product.getPrice());

        cartProductRepository.save(newCartProductEntity);
    }

    @Transactional
    public void removeProductInCart(long cartId, long productId) {
        CartEntity cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException(CartErrorCode.CART_NOT_FOUND));

        CartProductEntity cartProduct = cartProductRepository
                .findByCartCartIdAndProductProductIdAndIsDeletedIsFalse(cartId, productId)
                .orElseThrow(() -> new CartHasNotThisProductException(CartProductErrorCode.HAS_NOT_THIS_PRODUCT));

        cart.updateTotalPrice(-cartProduct.getProduct().getPrice());

        cartProduct.setDeleted();
    }

    public CartEntity findByMemberId(long memberId) {
        Optional<CartEntity> cart = cartRepository.findByMemberMemberIdAndIsDeletedIsFalse(memberId);

        if(cart.isEmpty()){
            MemberEntity member = memberService.findById(memberId);
            return cartRepository.save(new CartEntity(member, 0));
        }

        return cart.get();
    }

    public boolean matchMemberId(long cartId, long memberId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new CartHasNotThisProductException(CartProductErrorCode.HAS_NOT_THIS_PRODUCT))
                .getMember().getMemberId() == memberId;
    }
}
