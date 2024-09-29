package com.example.danggunmarket.member.exception;

import com.example.danggunmarket.common.exception.DanggunMarketException;
import com.example.danggunmarket.common.exception.ErrorCode;

public class MemberNotFoundException extends DanggunMarketException {
    public MemberNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public MemberNotFoundException(ErrorCode errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }
}
