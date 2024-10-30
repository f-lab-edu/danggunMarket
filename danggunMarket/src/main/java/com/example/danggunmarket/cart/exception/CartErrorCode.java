package com.example.danggunmarket.cart.exception;

import com.example.danggunmarket.common.exception.ErrorCode;


public enum CartErrorCode implements ErrorCode {

    CART_NOT_FOUND(404, "해당 장바구니는 존재하지 않습니다.");

    private final int statusCode;
    private final String message;

    CartErrorCode(int statusCode, String message) {
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
