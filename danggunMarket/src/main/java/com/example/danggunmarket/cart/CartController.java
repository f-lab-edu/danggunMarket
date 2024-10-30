package com.example.danggunmarket.cart;

import com.example.danggunmarket.cart.dto.GetCartResponse;
import com.example.danggunmarket.cartProduct.dto.AddProductToCartRequest;
import com.example.danggunmarket.common.auth.LoggedInMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/v1/carts")
    public ResponseEntity<Void> addProductToCart(@RequestBody AddProductToCartRequest request,
                                                 @AuthenticationPrincipal LoggedInMember loggedInMember) {
        cartService.addProductToCart(request, loggedInMember);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/carts")
    public ResponseEntity<GetCartResponse> getProductInCart(@AuthenticationPrincipal LoggedInMember member) {
        GetCartResponse response = cartService.getCart(member.getId());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/v1/carts/{cartId}/products/{productId}")
    public ResponseEntity<Void> removeProductInCart(@PathVariable("cartId") long cartId,
                                                    @PathVariable("productId") long productId) {
        cartService.removeProductInCart(cartId, productId);
        return ResponseEntity.ok().build();
    }
}
