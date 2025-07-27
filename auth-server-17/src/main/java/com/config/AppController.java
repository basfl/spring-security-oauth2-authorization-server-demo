package com.config;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class AppController {
	@GetMapping("/login")
	public String login(@AuthenticationPrincipal Jwt jwt) {
		System.out.println("************");
		return "login";
	}
	
	@GetMapping("/hello")
	public String hello() {

		return "hello";
	}

	@GetMapping("/start")
	public String startOAuth(@RequestParam String client_id, @RequestParam String redirect_uri,
			@RequestParam String scope) {
		String url = UriComponentsBuilder.fromUriString("/oauth2/authorize").queryParam("response_type", "code")
				.queryParam("client_id", client_id).queryParam("scope", scope).queryParam("redirect_uri", redirect_uri)
				.build().toUriString();

		return "redirect:" + url;
	}

}