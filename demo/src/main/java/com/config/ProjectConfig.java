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
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.repository.UserRepository;
import com.services.MyClientService;
//import com.services.MyUserDetailsService;

@Configuration
//@Import(OAuth2AuthorizationServerConfiguration.class)
public class ProjectConfig {

	@Autowired
	UserRepository userRepository;
//	@Autowired
//	MyUserDetailsService myUserDetailsService;
	@Autowired
	MyClientService myClientService;

//	@Bean
//	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated()).formLogin(Customizer.withDefaults());
//			//	.loginPage("/login").permitAll();
//
//		return http.build();
//	}
	
	
	 @Bean
	    @Order(Ordered.HIGHEST_PRECEDENCE)
	    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
	        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

	        return http.formLogin(Customizer.withDefaults()).build();
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

	@Bean
	public ProviderSettings providerSettings() {
//		ProviderSettings ps = new ProviderSettings();
//		ps = ps.issuer("http://localhost:9000");
//		ps = ps.jwkSetEndpoint("/certs");
//		return ps;
		return ProviderSettings.builder().issuer("http://localhost:9000").build();
	}

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
}