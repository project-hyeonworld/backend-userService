package io.userservice.common.exception;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 30.
 */
@Getter
@RequiredArgsConstructor
public enum ExceptionResponseCode {
    USER_ALREADY_LOGGED_IN(CONFLICT, "The user is already Logged in"),
    USER_NOT_FOUND(NOT_FOUND, "The user is not in DB userId : %d");

    private final HttpStatus httpStatus;
    private final String message;
}
