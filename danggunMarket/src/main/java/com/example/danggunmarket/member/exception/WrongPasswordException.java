package com.example.danggunmarket.member.exception;

import com.example.danggunmarket.common.exception.DanggunMarketException;
import com.example.danggunmarket.common.exception.ErrorCode;

public class WrongPasswordException extends DanggunMarketException {
    public WrongPasswordException(ErrorCode errorCode) {
        super(errorCode);
    }

    public WrongPasswordException(ErrorCode errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }
}
