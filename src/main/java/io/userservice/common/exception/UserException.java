package io.userservice.common.exception;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 30.
 */
public class UserException extends ServerException {

    public UserException(ExceptionResponseCode code) {
        this(code, "runtimeMessage is not available");
    }

    public UserException(ExceptionResponseCode code, String runtimeMessage) {
        super(code, runtimeMessage);
    }
}
