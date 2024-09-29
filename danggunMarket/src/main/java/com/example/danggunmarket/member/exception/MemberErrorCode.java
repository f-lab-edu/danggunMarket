package com.example.danggunmarket.member.exception;

import com.example.danggunmarket.common.exception.ErrorCode;


public enum MemberErrorCode implements ErrorCode {

    ALREADY_EXIST_EMAIL(400, "이미 존재하는 이메일입니다"),
    ALREADY_EXIST_NICKNAME(400, "사용할 수 없는 닉네임입니다"),
    NOT_FOUND_MEMBER(404, "존재하지 않는 유저입니다."),

    NOT_MATCHED_PASSWORD(400, "비밀번호가 일치하지 않습니다.");

    private final int statusCode;
    private final String message;

    MemberErrorCode(int statusCode, String message) {
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
