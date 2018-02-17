package com.ai.sin.currency.exception;

/**
 * General exception for SinCurrency Application
 */
public class SinCurrencyException extends RuntimeException {
    public SinCurrencyException() {
    }

    public SinCurrencyException(String message) {
        super(message);
    }

    public SinCurrencyException(String message, Throwable cause) {
        super(message, cause);
    }

    public SinCurrencyException(Throwable cause) {
        super(cause);
    }

    public SinCurrencyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
