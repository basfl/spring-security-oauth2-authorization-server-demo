package com.config;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import com.filters.RequestResponseLoggingFilter;





@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)

public class WebSecurity  {

	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	 System.out.println("i am hereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
	 JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new RoleConverter());
        http.mvcMatcher("/api/**")
          .authorizeRequests()
          .mvcMatchers("/api/**")
          .access("hasRole('ROLE_USER') or hasRole('ROLE_USER_OR_CLIENT') or hasRole('ROLE_CLIENT')")
          .and()
          .oauth2ResourceServer()
          .jwt().jwtAuthenticationConverter(jwtAuthenticationConverter);
        return http.build();
    }
	
	@Bean
	public FilterRegistrationBean<RequestResponseLoggingFilter> loggingFilter(){
	    FilterRegistrationBean<RequestResponseLoggingFilter> registrationBean 
	      = new FilterRegistrationBean<>();
	        
	    registrationBean.setFilter(new RequestResponseLoggingFilter());
	    registrationBean.addUrlPatterns("/api/*");
	    registrationBean.setOrder(2);
	        
	    return registrationBean;    
	}
}
