package com.minihouse.exception;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

    protected BaseException() {
        super();
    }

    protected BaseException(String message) {
        super(message);
    }

    protected BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();
}
