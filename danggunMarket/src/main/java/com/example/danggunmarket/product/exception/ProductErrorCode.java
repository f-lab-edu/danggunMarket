package com.example.danggunmarket.product.exception;

import com.example.danggunmarket.common.exception.ErrorCode;


public enum ProductErrorCode implements ErrorCode {

    NOT_FOUND_PRODUCT(404, "존재하지 않는 상품입니다."),
    NOT_VALID_ID(400, "유효하지 않은 상품 ID입니다"),
    NOT_AUTHORIZED_PRODUCT(403, "사용자는 상품 판매자가 아닙니다");

    private final int statusCode;
    private final String message;

    ProductErrorCode(int statusCode, String message) {
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
