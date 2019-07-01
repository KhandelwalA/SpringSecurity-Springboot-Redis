package com.khandelwal.SpringbootRedis.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	/**
	 * Setting in memory client details ie client id, client secret
	 * @throws Exception 
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		clients.inMemory()
		.withClient("clientId")
		.secret(passwordEncoder.encode("secret"))
		.authorizedGrantTypes("authorization_code", "password", "implicit", "client_credentials", "device_code")
		.scopes("READ","WRITE")
		.accessTokenValiditySeconds(3600)
		.autoApprove(true);
	}

	/**
	 * This method tells to allow access token to everyone and to authenticate the
	 * access token before allow to use
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) {

		security
		.tokenKeyAccess("permitAll")
		.checkTokenAccess("isAuthenticated");
	}
	
	@Override
	public void configure (AuthorizationServerEndpointsConfigurer endpointConfigurer) {
		
		endpointConfigurer.authenticationManager(authenticationManager);
	}
}
