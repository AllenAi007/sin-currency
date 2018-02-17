package com.ai.sin.currency.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO
 */
public class SinCurrencyDto<T> {

    @JsonProperty("header")
    private Header header;
    @JsonProperty("payload")
    private T payload;
    @JsonProperty("error")
    private Error error;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    /**
     * Return new success response DTO
     * @param payload
     * @param <T>
     * @return
     */
    public static <T> SinCurrencyDto<T> success(T payload) {
        SinCurrencyDto<T> t = new SinCurrencyDto<>();
        Header header = new Header();
        header.setSuccess(true);
        t.setHeader(header);
        t.setPayload(payload);
        return t;
    }

    public static class Header {
        private boolean success;
        private String authToken;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getAuthToken() {
            return authToken;
        }

        public void setAuthToken(String authToken) {
            this.authToken = authToken;
        }
    }

    public static class Error {

        private String errorCode;
        private String errorType;
        private String errorMessage;

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorType() {
            return errorType;
        }

        public void setErrorType(String errorType) {
            this.errorType = errorType;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
