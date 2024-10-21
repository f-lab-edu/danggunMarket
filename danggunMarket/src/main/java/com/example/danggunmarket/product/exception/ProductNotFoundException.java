package com.example.danggunmarket.product.exception;

import com.example.danggunmarket.common.exception.DanggunMarketException;
import com.example.danggunmarket.common.exception.ErrorCode;

public class ProductNotFoundException extends DanggunMarketException {
    public ProductNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ProductNotFoundException(ErrorCode errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }
}
