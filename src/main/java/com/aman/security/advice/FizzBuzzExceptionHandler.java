package com.aman.security.advice;

import com.aman.security.dto.GlobalError;
import com.aman.security.exception.FizzException;
import com.aman.security.exception.BuzzException;
import com.aman.security.exception.FizzBuzzException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FizzBuzzExceptionHandler {
    
    @ExceptionHandler(FizzException.class)
    public ResponseEntity<GlobalError> handleFizzException(FizzException e) {
        GlobalError error = new GlobalError(e.getMessage(), "Internal Server Error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
    
    @ExceptionHandler(BuzzException.class)
    public ResponseEntity<GlobalError> handleBuzzException(BuzzException e) {
        GlobalError error = new GlobalError(e.getMessage(), "Bad Request");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    
    @ExceptionHandler(FizzBuzzException.class)
    public ResponseEntity<GlobalError> handleFizzBuzzException(FizzBuzzException e) {
        GlobalError error = new GlobalError(e.getMessage(), "Insufficient Storage");
        return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE).body(error);
    }
}
