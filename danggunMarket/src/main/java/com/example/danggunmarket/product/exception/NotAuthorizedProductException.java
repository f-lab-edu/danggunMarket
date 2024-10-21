package com.example.danggunmarket.product.exception;

import com.example.danggunmarket.common.exception.DanggunMarketException;
import com.example.danggunmarket.common.exception.ErrorCode;

public class NotAuthorizedProductException extends DanggunMarketException {
    public NotAuthorizedProductException(ErrorCode errorCode) {
        super(errorCode);
    }

    public NotAuthorizedProductException(ErrorCode errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }
}
