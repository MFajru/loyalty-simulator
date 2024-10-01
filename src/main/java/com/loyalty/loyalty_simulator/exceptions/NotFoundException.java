package com.loyalty.loyalty_simulator.exceptions;

public class NotFoundException extends RuntimeException {
    private final String code;
    public NotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }

    public NotFoundException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
