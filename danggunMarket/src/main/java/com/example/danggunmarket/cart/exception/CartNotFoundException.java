package com.example.danggunmarket.cart.exception;

import com.example.danggunmarket.common.exception.DanggunMarketException;
import com.example.danggunmarket.common.exception.ErrorCode;

public class CartNotFoundException extends DanggunMarketException {
    public CartNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public CartNotFoundException(ErrorCode errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }
}
