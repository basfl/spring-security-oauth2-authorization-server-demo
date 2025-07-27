package com.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.repository.UserRepository;
import com.services.MyClientService;
//import com.services.MyClientService;
//import com.services.MyClientService;
//import com.services.MyUserDetailsService;

@Configuration
//@Import(OAuth2AuthorizationServerConfiguration.class)
public class ProjectConfig {

	@Autowired
	UserRepository userRepository;
	@Autowired
	MyClientService myClientService;

//	@Bean
//	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated()).formLogin(Customizer.withDefaults());
//			//	.loginPage("/login").permitAll();
//
//		return http.build();
//	}
	
	
//	 @Bean
//	    @Order(Ordered.HIGHEST_PRECEDENCE)
//	    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
//	        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
//
//	        return http.formLogin(Customizer.withDefaults()).build();
//	    }
	 
	@Bean 
	@Order(1)
	public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
			throws Exception {
		
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
		http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
			.oidc(Customizer.withDefaults());	// Enable OpenID Connect 1.0
		http
			// Redirect to the login page when not authenticated from the
			// authorization endpoint
			.exceptionHandling((exceptions) -> exceptions
				.authenticationEntryPoint(
					new LoginUrlAuthenticationEntryPoint("/login"))
			)
			// Accept access tokens for User Info and/or Client Registration
			.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

		return http.build();
	}

	

	
	 @Bean 
		@Order(2)
		public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
				throws Exception {
//			http
//				.authorizeHttpRequests((authorize) -> authorize
//					.anyRequest().authenticated()
//				)
//				// Form login handles the redirect to the login page from the
//				// authorization server filter chain
//				.formLogin(Customizer.withDefaults());
//
//			return http.build();
		 
				 
		 
		 http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
					.requestMatchers
					("/registration**",
			                "/js/**",
			                "/css/**",
			                "/images/**").permitAll()
					.anyRequest().authenticated()).httpBasic().disable()
		 .formLogin()
				.loginPage("/login").defaultSuccessUrl("/hello", true)
				.permitAll();

			return http.build();
		}

	
	@Bean
	public OAuth2TokenCustomizer<JwtEncodingContext>  tokenCustomizer(){
		
		System.out.println("customize token");
		return context->{
			
			Authentication principle = context.getPrincipal();
			 Set<String> authorities = principle.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
			 context.getClaims().claim("authorities", authorities);
			
		};
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public JWKSource<SecurityContext> jwkSource() {
		RSAKey rsaKey = getKeyPair();
		JWKSet jwkSet = new JWKSet(rsaKey);
		return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
	}

//	@Bean
//	public ProviderSettings providerSettings() {
//		ProviderSettings ps = new ProviderSettings();
//		ps = ps.issuer("http://localhost:9000");
//		ps = ps.jwkSetEndpoint("/certs");
//		return ps;
// **		return ProviderSettings.builder().issuer("http://localhost:9000").build();
//	}

	private RSAKey getKeyPair() {
		KeyPair keyPair = generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

		return new RSAKey.Builder(publicKey).privateKey(privateKey).keyID(UUID.randomUUID().toString()).build();
	}

	private KeyPair generateKeyPair() {
		KeyPair keyPair;
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			keyPair = keyPairGenerator.generateKeyPair();
		} catch (Exception ex) {
			throw new IllegalStateException(ex);
		}
		return keyPair;
	}
	
	
	@Bean 
	public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
		return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
	}
	
	@Bean 
	public AuthorizationServerSettings authorizationServerSettings() {
		return AuthorizationServerSettings.builder().build();
	}
}