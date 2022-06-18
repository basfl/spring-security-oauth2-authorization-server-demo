package com.controller;

import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

	@GetMapping("/all")
	//@PreAuthorize("hasRole('ROLE_USER')")
	public String getDemo(@AuthenticationPrincipal Jwt  jwt) {
		
		String id = jwt.getSubject();
		Object scope = jwt.getClaim("scope");
		Map<String, Object> claims = jwt.getClaims();
		claims.forEach((k, v) -> {
			System.out.println("key is " + k + " value is " + v);
		});
		System.out.println("****id " + id + " scope is " + scope);
		return "here & princliple "+jwt;

	}
	
	@GetMapping("/only")
	@PreAuthorize("hasAuthority('SCOPE_openid')")
	public String getClient(@AuthenticationPrincipal Jwt  jwt) {
		
		String id = jwt.getSubject();
		Object scope = jwt.getClaim("scope");
		Map<String, Object> claims = jwt.getClaims();
		claims.forEach((k, v) -> {
			System.out.println("key is " + k + " value is " + v);
		});
		System.out.println("****id " + id + " scope is " + scope);
		return "only client  & princliple "+jwt;

	}

}
