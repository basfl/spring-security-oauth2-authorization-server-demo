package com.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
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
				.clientId(client.get(0).getClientId()).clientSecret(client.get(0).getSecret())
				.clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
				.redirectUri(client.get(0).getRedirect()).scope("openid")
				.clientSettings(clientSettings -> clientSettings.requireUserConsent(true)).build();
		return registeredClient;
	}

}
