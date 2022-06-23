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
    	
    	System.out.println("#################!!!!"+jwt.getSubject());
    	
  
    //    Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("scope");
    	java.util.List<GrantedAuthority> authority = new ArrayList<GrantedAuthority>();
    	
    	Object realmAccess = jwt.getClaim("scope");
    	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    	Map<String, Object> claims = jwt.getClaims();
		claims.forEach((k, v) -> {
			System.out.println("key is " + k + " value is " + v);
		});
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (realmAccess == null ) {
        	
        	/////////////////////////////////////////
        	System.out.println("adding new role for user **********");
        	
        	GrantedAuthority grantAuthority = new UserGrantedAuthority("ROLE_USER");
        	GrantedAuthority grantAuthority1 = new UserGrantedAuthority("ROLE_USER_OR_CLIENT");
    		authority.add(grantAuthority);
    		authority.add(grantAuthority1);
        	///////////////////////////////////////////
            return authority;
        } 
        System.out.println("adding client role ");
        GrantedAuthority grantAuthority = new UserGrantedAuthority("ROLE_CLIENT");
        
		authority.add(grantAuthority);

//        Collection<GrantedAuthority> returnValue = ((List<String>) realmAccess.get("roles"))
//                .stream().map(roleName -> "ROLE_" + roleName)  
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
 
        return authority;
    }

}
