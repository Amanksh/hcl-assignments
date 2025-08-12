package com.aman.security.exception;

public class FizzBuzzException extends RuntimeException {
    
    public FizzBuzzException() {
        super("FizzBuzz Exception has been thrown");
    }
    
    public FizzBuzzException(String message) {
        super(message);
    }
}
