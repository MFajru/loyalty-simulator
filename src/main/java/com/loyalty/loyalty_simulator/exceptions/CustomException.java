package com.loyalty.loyalty_simulator.exceptions;

public class CustomException extends RuntimeException {
    private final String code;
    public CustomException(String message, String code) {
        super(message);
        this.code = code;
    }

    public CustomException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
