package com.upFile.utils.Exceptions;

public class IllegalInputException extends RuntimeException {


    public IllegalInputException() {
        super();
    }

    public IllegalInputException(String message) {
        super(message);
    }

    public IllegalInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalInputException(Throwable cause) {
        super(cause);
    }

    protected IllegalInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
