package com.services;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserGrantedAuthority implements GrantedAuthority {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String authority;
	
	

	@Override
	public String getAuthority() {
		
		return authority;
	}

}
