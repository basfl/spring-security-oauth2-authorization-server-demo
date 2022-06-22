package com.config;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AppController {
	@GetMapping("/login")
	public String login(@AuthenticationPrincipal Jwt jwt) {
		System.out.println("************");
		return "login";
	}

}