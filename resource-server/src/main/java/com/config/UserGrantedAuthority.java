package com.config;

import org.springframework.security.core.GrantedAuthority;


public class UserGrantedAuthority implements GrantedAuthority {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String authority;
	
	

	public UserGrantedAuthority(String authority) {
		super();
		this.authority = authority;
	}



	@Override
	public String getAuthority() {
		
		return authority;
	}

}
