package com.example.danggunmarket.common.exception;

import com.example.danggunmarket.common.exception.dto.ResultError;
import com.example.danggunmarket.member.exception.AlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ResultError<String>> alreadyExistException(AlreadyExistException exception) {
        ErrorCode errorCode = exception.getErrorCode();

        return ResponseEntity.status(errorCode.getStatusCode())
                .body(new ResultError<>(errorCode.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultError<Map<String, String>>> methodNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> map = new HashMap<>();

        exception.getBindingResult().getFieldErrors()
                .forEach(fieldError -> map.put(fieldError.getField(), fieldError.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResultError<>(map));
    }
}
