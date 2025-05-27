package dev.waiyanhtet.samplecodetest.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<ErrorResponse> handleBusinessLogicException(BusinessLogicException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getCode(), ex.getMessage(), LocalDateTime.now(), request.getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getCode()));
    }
}
