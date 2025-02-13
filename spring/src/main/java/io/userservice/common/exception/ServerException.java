package io.userservice.common.exception;

import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 30.
 */
@Getter
public class ServerException extends RuntimeException {

    protected final ExceptionResponseCode code;

    public ServerException(ExceptionResponseCode code) {
        this(code, "runtimeMessage is not available");
    }

    public ServerException(ExceptionResponseCode code, String runtimeMessage) {
        super(code.getMessage() + ": " + runtimeMessage);
        this.code = code;
    }
}