package com.example.book_systems.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAndPersoninfoController {

	@GetMapping(value = "login")
	public void login() {
		
	}
	
	@PostMapping(value = "sign")
	public void sign() {
		
	}
}
