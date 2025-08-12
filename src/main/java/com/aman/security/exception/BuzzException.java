package com.aman.security.exception;

public class BuzzException extends RuntimeException {
    
    public BuzzException() {
        super("Buzz Exception has been thrown");
    }
    
    public BuzzException(String message) {
        super(message);
    }
}
