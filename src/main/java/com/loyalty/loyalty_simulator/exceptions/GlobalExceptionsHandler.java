package com.loyalty.loyalty_simulator.exceptions;

import com.loyalty.loyalty_simulator.dto.GeneralErrorResponse;
import com.loyalty.loyalty_simulator.dto.ResponseData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(CustomException.class)
    public <T> ResponseEntity<ResponseData<T>> handleNotFoundException(CustomException exception) {
        ResponseData<T> res = new ResponseData<T>();
        res.setMessage(exception.getMessage());
        res.setData(null);
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralErrorResponse> handleInternalException(Exception exception, HttpServletRequest request) {
        GeneralErrorResponse errorResponse = new GeneralErrorResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), request.getRequestURI(), request.getMethod(), LocalDateTime.now());
        return ResponseEntity.internalServerError().body(errorResponse);
    }
}
