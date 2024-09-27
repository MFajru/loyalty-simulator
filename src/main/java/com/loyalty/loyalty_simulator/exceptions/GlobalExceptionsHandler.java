package com.loyalty.loyalty_simulator.exceptions;

import com.loyalty.loyalty_simulator.dto.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionsHandler extends RuntimeException {
    public GlobalExceptionsHandler(String message) {
        super(message);
    }

    @ExceptionHandler(NotFoundException.class)
    public <T> ResponseEntity<ResponseData<T>> handleNotFoundException(NotFoundException exception) {
        ResponseData<T> res = new ResponseData<T>();
        res.setMessage(exception.getMessage());
        res.setData(null);
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }
}
