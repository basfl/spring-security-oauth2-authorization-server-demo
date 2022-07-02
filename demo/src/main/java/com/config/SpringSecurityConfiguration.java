package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
	

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorizeRequests -> authorizeRequests
				.antMatchers("/registration**",
		                "/js/**",
		                "/css/**",
		                "/images/**").permitAll()
				.anyRequest().authenticated()).formLogin()
			.loginPage("/login").permitAll();

		return http.build();
	}
	
	 public void configure(WebSecurity web) throws Exception {
         web
                 .ignoring()
                 .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/vendor/**","/fonts/**");
     }
	
}
