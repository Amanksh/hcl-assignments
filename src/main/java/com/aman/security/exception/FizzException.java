package com.aman.security.exception;

public class FizzException extends RuntimeException {
    
    public FizzException() {
        super("Fizz Exception has been thrown");
    }
    
    public FizzException(String message) {
        super(message);
    }
}
