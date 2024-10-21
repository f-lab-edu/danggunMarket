package com.example.danggunmarket.product.exception;

import com.example.danggunmarket.common.exception.DanggunMarketException;
import com.example.danggunmarket.common.exception.ErrorCode;

public class InValidProductIdException extends DanggunMarketException {
    public InValidProductIdException(ErrorCode errorCode) {
        super(errorCode);
    }

    public InValidProductIdException(ErrorCode errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }
}
