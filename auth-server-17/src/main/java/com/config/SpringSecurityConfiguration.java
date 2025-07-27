package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SpringSecurityConfiguration {

//	@Bean
//	SecurityFilterChain configureSecurityFilterChain(HttpSecurity http) throws Exception {
//		
//		http
//		.authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
//		.formLogin(Customizer.withDefaults());
//		
//		return http.build();
//		
//	}
	
                   /////was ////////////
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		//http.authorizeRequests(authorizeRequests->authorizeRequests.an)
		http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
				.requestMatchers
				("/registration**",
		                "/js/**",
		                "/css/**",
		                "/images/**").permitAll()
				.anyRequest().authenticated()).httpBasic().disable()
		.formLogin()
			.loginPage("/login").permitAll();
			
		

		return http.build();
	}
	
	 public void configure(WebSecurity web) throws Exception {
		
         web
                 .ignoring().requestMatchers
                 ("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/vendor/**","/fonts/**");
     }
	
}
