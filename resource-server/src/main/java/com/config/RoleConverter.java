package com.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;



public class RoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
	
	public RoleConverter() {
		System.out.println("converter*******");
	}

    @Override
    public Collection<GrantedAuthority> convert(final Jwt jwt) {
    	
    	System.out.println("*****convertering roles*******");

		//////////////////////////////////////////////////////////////////////
		java.util.List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		java.util.List<String> grantedAuthorityList = jwt.getClaim("authorities");

		if (grantedAuthorityList.size() > 0) {
			System.out.println("converting user authorities");
			grantedAuthorityList.forEach(authority -> {
				GrantedAuthority grantAuthority = new UserGrantedAuthority(authority);
				authorities.add(grantAuthority);

			});
		} else {

			if (jwt.getClaim("scope") != null) {
				System.out.println("converting client authorities");
				GrantedAuthority clientGrantAuthority = new UserGrantedAuthority("ROLE_CLIENT");
				authorities.add(clientGrantAuthority);

			}
		}

		return authorities;
    }

}
