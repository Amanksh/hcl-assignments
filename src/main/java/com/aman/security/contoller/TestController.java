package com.aman.security.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class TestController {
	
	@GetMapping("/test")
	public String getTest() {
		return "Testing public routes";
	}
}
