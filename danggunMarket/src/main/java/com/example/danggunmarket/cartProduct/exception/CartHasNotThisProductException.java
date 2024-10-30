package com.example.danggunmarket.cartProduct.exception;

import com.example.danggunmarket.common.exception.DanggunMarketException;
import com.example.danggunmarket.common.exception.ErrorCode;

public class CartHasNotThisProductException extends DanggunMarketException {
    public CartHasNotThisProductException(ErrorCode errorCode) {
        super(errorCode);
    }

    public CartHasNotThisProductException(ErrorCode errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }
}
