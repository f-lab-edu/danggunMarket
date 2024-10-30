package com.example.danggunmarket.cartProduct.exception;

import com.example.danggunmarket.common.exception.ErrorCode;


public enum CartProductErrorCode implements ErrorCode {

    HAS_NOT_THIS_PRODUCT(400, "장바구니에 해당 상품이 존재하지 않습니다.");

    private final int statusCode;
    private final String message;

    CartProductErrorCode(int statusCode, String message) {
        this.message = message;
        this.statusCode = statusCode;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
