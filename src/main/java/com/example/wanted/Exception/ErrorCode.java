package com.example.wanted.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "user is duplicated"),
    USER_NOT_FOUNDED(HttpStatus.NOT_FOUND, "not found user"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "password is wrong");

    private HttpStatus httpStatus;
    private String message;
}
