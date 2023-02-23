package com.minihouse.exception;

public class PasswordNotMatchedException extends BaseException {

    public PasswordNotMatchedException() {
        super();
    }

    public PasswordNotMatchedException(String message) {
        super(message);
    }

    public PasswordNotMatchedException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
