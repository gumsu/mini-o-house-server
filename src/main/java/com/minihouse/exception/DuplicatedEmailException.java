package com.minihouse.exception;

public class DuplicatedEmailException extends BaseException {

    public DuplicatedEmailException() {
        super();
    }

    public DuplicatedEmailException(String message) {
        super(message);
    }

    public DuplicatedEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
