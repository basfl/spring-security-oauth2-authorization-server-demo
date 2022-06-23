package com.controller;

import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.controller.errorHandler.CustomException;
import com.controller.errorHandler.ResourceAlreadyExists;
import com.controller.errorHandler.ResourceNotFoundException;
import com.controller.errorHandler.UnauthorizedException;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

	@GetMapping("/all")
	//@PreAuthorize("hasRole('ROLE_USER')")
	public String getDemo(@AuthenticationPrincipal Jwt  jwt,@CurrentSecurityContext SecurityContext context) {
		System.out.println("context"+context.getAuthentication().getName());
		context.getAuthentication().getAuthorities().forEach(auth->{
			System.out.println("auth^^^^"+auth.getAuthority());
		});
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
	public String getClient(@AuthenticationPrincipal Jwt  jwt,@CurrentSecurityContext SecurityContext context) {
		context.getAuthentication().getAuthorities().forEach(auth->{
			System.out.println("auth^^^^"+auth.getAuthority());
		});
		String id = jwt.getSubject();
		Object scope = jwt.getClaim("scope");
		Map<String, Object> claims = jwt.getClaims();
		claims.forEach((k, v) -> {
			System.out.println("key is " + k + " value is " + v);
		});
		System.out.println("****id " + id + " scope is " + scope);
		return "only client  & princliple "+jwt;

	}
	
	@GetMapping(value = "/testExceptionHandling")
			public String testExceptionHandling(@RequestParam int code) {
			    switch (code) {
			        case 401:
			            throw new UnauthorizedException("You are not authorized");
			        case 404:
			            throw new ResourceNotFoundException("Requested resource is not found.");
			        case 400:
			            throw new CustomException("Please provide resource id.");
			        case 409:
			            throw new ResourceAlreadyExists("Resource already exists in DB.");

			        default:
			            return "Yeah...No Exception.";

			    }
			}

}
