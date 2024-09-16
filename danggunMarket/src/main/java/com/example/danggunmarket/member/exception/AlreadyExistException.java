package com.example.danggunmarket.member.exception;

import com.example.danggunmarket.common.exception.DanggunMarketException;
import com.example.danggunmarket.common.exception.ErrorCode;

public class AlreadyExistException extends DanggunMarketException {

    public AlreadyExistException(ErrorCode errorCode) {
        super(errorCode);
    }

    public AlreadyExistException(ErrorCode errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }
}
