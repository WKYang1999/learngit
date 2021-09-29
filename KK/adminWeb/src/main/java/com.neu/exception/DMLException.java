package com.neu.exception;

public class DMLException extends RuntimeException {

    public DMLException() {
        super();
    }

    public DMLException(String message) {
        super(message);
    }

    public DMLException(String message, Throwable cause) {
        super(message, cause);
    }
    public DMLException(Throwable cause) {
        super(cause);
    }
}
