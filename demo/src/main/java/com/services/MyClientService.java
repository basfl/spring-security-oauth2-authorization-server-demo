package com.services;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

import com.entity.Client;
import com.repository.ClientRepository;

@Service
public class MyClientService implements RegisteredClientRepository {
	@Autowired
	ClientRepository clientRepo;

	@Override
	public RegisteredClient findById(String id) {
		// TODO Auto-generated method stub
		return null;
		

	}
	


	@Override
	public RegisteredClient findByClientId(String clientId) {
		System.out.println("******************loading client " + clientId);
		List<Client> client = clientRepo.findByClientId(clientId);
		System.out.println("client--"+client.get(0).getRedirect());
		// scope(OidcScopes.OPENID)

		RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
			//	.tokenSettings(tokenSetting->tokenSetting.accessTokenTimeToLive(Duration.ofHours(1)))
				.clientId(client.get(0).getClientId()).clientSecret(client.get(0).getSecret())
				.tokenSettings(TokenSettings.builder().accessTokenTimeToLive(Duration.ofHours(1)).build())
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
			//	.redirectUri("http://127.0.0.1:8080/login/oauth2/code/users-client-oidc")
				.redirectUri(client.get(0).getRedirect()).scope("openid").build();
		//		.clientSettings(clientSettings -> clientSettings.requireUserConsent(true)).build();
		return registeredClient;
	}

	@Override
	public void save(RegisteredClient registeredClient) {
		// TODO Auto-generated method stub
		
	}

}
