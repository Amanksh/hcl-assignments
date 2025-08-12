package com.aman.security.contoller;

import com.aman.security.dto.FizzBuzzResponse;
import com.aman.security.exception.FizzException;
import com.aman.security.exception.BuzzException;
import com.aman.security.exception.FizzBuzzException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controller_advice")
public class FizzBuzzController {
    
    @GetMapping("/{code}")
    public ResponseEntity<FizzBuzzResponse> getFizzBuzz(@PathVariable String code) {
        switch (code.toLowerCase()) {
            case "fizz":
                throw new FizzException();
            case "buzz":
                throw new BuzzException();
            case "fizzbuzz":
                throw new FizzBuzzException();
            case "success":
                FizzBuzzResponse response = new FizzBuzzResponse("Successfully completed fizzbuzz test", "200");
                return ResponseEntity.ok(response);
            default:
                // For any other code, return a default response
                FizzBuzzResponse defaultResponse = new FizzBuzzResponse("Code: " + code, "200");
                return ResponseEntity.ok(defaultResponse);
        }
    }
}
