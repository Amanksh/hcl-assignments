package com.aman.security;

import com.aman.security.dto.FizzBuzzResponse;
import com.aman.security.dto.GlobalError;
import com.aman.security.exception.FizzException;
import com.aman.security.exception.BuzzException;
import com.aman.security.exception.FizzBuzzException;
import com.aman.security.advice.FizzBuzzExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FizzBuzzControllerAdviceTests {

    @Autowired
    private FizzBuzzExceptionHandler exceptionHandler;

    @Test
    public void testFizzExceptionHandler() {
        FizzException fizzException = new FizzException();
        ResponseEntity<GlobalError> response = exceptionHandler.handleFizzException(fizzException);
        
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Fizz Exception has been thrown", response.getBody().getMessage());
        assertEquals("Internal Server Error", response.getBody().getErrorReason());
    }

    @Test
    public void testBuzzExceptionHandler() {
        BuzzException buzzException = new BuzzException();
        ResponseEntity<GlobalError> response = exceptionHandler.handleBuzzException(buzzException);
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Buzz Exception has been thrown", response.getBody().getMessage());
        assertEquals("Bad Request", response.getBody().getErrorReason());
    }

    @Test
    public void testFizzBuzzExceptionHandler() {
        FizzBuzzException fizzBuzzException = new FizzBuzzException();
        ResponseEntity<GlobalError> response = exceptionHandler.handleFizzBuzzException(fizzBuzzException);
        
        assertEquals(HttpStatus.INSUFFICIENT_STORAGE, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("FizzBuzz Exception has been thrown", response.getBody().getMessage());
        assertEquals("Insufficient Storage", response.getBody().getErrorReason());
    }



    @Test
    public void testExceptionClasses() {
        // Test FizzException
        FizzException fizzException = new FizzException();
        assertEquals("Fizz Exception has been thrown", fizzException.getMessage());
        
        FizzException fizzExceptionCustom = new FizzException("Custom Fizz Message");
        assertEquals("Custom Fizz Message", fizzExceptionCustom.getMessage());

        // Test BuzzException
        BuzzException buzzException = new BuzzException();
        assertEquals("Buzz Exception has been thrown", buzzException.getMessage());
        
        BuzzException buzzExceptionCustom = new BuzzException("Custom Buzz Message");
        assertEquals("Custom Buzz Message", buzzExceptionCustom.getMessage());

        // Test FizzBuzzException
        FizzBuzzException fizzBuzzException = new FizzBuzzException();
        assertEquals("FizzBuzz Exception has been thrown", fizzBuzzException.getMessage());
        
        FizzBuzzException fizzBuzzExceptionCustom = new FizzBuzzException("Custom FizzBuzz Message");
        assertEquals("Custom FizzBuzz Message", fizzBuzzExceptionCustom.getMessage());
    }

    @Test
    public void testResponseClasses() {
        // Test GlobalError
        GlobalError globalError = new GlobalError("Test Message", "Test Reason");
        assertEquals("Test Message", globalError.getMessage());
        assertEquals("Test Reason", globalError.getErrorReason());

        // Test FizzBuzzResponse
        FizzBuzzResponse fizzBuzzResponse = new FizzBuzzResponse("Test Message", "200");
        assertEquals("Test Message", fizzBuzzResponse.getMessage());
        assertEquals("200", fizzBuzzResponse.getStatusCode());
    }
}
