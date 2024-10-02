package com.loyalty.loyalty_simulator.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.loyalty.loyalty_simulator.dto.GeneralErrorResponse;
import com.loyalty.loyalty_simulator.dto.ResponseData;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.UUID;

@ControllerAdvice
public class GlobalExceptionsHandler {
    private final static Logger logger = LogManager.getLogger(GlobalExceptionsHandler.class);

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @ExceptionHandler(CustomException.class)
    public <T> ResponseEntity<ResponseData<T>> handleNotFoundException(CustomException exception, HttpServletRequest request) {
        ResponseData<T> res = new ResponseData<T>();
        res.setMessage(exception.getMessage());
        res.setData(null);
        logger.warn(res.getMessage());
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralErrorResponse> handleInternalException(Exception exception, HttpServletRequest request) {
        String uuid = UUID.randomUUID().toString();
        GeneralErrorResponse errorResponse = new GeneralErrorResponse(uuid,exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), exception.getStackTrace()[0].toString(), request.getRequestURI(), request.getMethod(), LocalDateTime.now());
        try {
            logger.warn(objectMapper.writeValueAsString(errorResponse));
        } catch (JsonProcessingException e) {
            logger.warn("Failed to convert JSON", e);
        }
        return ResponseEntity.internalServerError().body(errorResponse);
    }
}
