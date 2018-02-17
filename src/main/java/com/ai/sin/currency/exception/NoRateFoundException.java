package com.ai.sin.currency.exception;

/**
 * Not able to find the rate
 */
public class NoRateFoundException extends SinCurrencyException {
    public NoRateFoundException() {
    }

    public NoRateFoundException(String message) {
        super(message);
    }

    public NoRateFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoRateFoundException(Throwable cause) {
        super(cause);
    }

    public NoRateFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
