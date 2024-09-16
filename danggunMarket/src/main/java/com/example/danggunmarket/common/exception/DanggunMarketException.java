package com.example.danggunmarket.common.exception;

import lombok.Getter;

@Getter
public class DanggunMarketException extends RuntimeException{

    private final ErrorCode errorCode;

    public DanggunMarketException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public DanggunMarketException(ErrorCode errorCode, Throwable throwable){
        super(errorCode.getMessage(), throwable);
        this.errorCode = errorCode;
    }
}
