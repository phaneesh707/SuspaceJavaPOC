package com.subspaceclone.backend.ErrorHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<CustomError> handleCustomException(CustomError e) {
        HttpStatus httpStatus;
        if (e.getErrorCode() == 404) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(e, httpStatus);
    }

    public static class CustomError extends RuntimeException {
        private Integer errorCode;
        private String errorMessage;

        public CustomError(Integer errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

        public Integer getErrorCode() {
            return errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}