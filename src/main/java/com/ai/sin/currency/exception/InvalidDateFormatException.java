package com.ai.sin.currency.exception;


/**
 * Invalid date format exception
 */
public class InvalidDateFormatException extends SinCurrencyException {
    public InvalidDateFormatException() {
    }

    public InvalidDateFormatException(String message) {
        super(message);
    }

    public InvalidDateFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDateFormatException(Throwable cause) {
        super(cause);
    }

    public InvalidDateFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
