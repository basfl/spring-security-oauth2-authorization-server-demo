package com.config;
import javax.print.attribute.HashPrintServiceAttributeSet;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;


@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)

//@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

//		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new ConvertJWT());
		 http.mvcMatcher("/api/**")
         .authorizeRequests()
         .antMatchers("/article/**").hasAnyRole("USER")
//         .mvcMatchers("/article/**")
//         .access("hasAuthority('*')")
        // .hasAuthority("SCOPE_openid")
        // .hasAnyRole("USER")
      //   .access("hasAuthority('SCOPE_articles.read')")
         .and()
         .oauth2ResourceServer()
         .jwt();
		 
		    //    return http.build();
		// .jwtAuthenticationConverter(jwtAuthenticationConverter);

	}

}
