package com.example.cas.client.demo.controller;

import javax.annotation.PostConstruct;

import org.pac4j.oauth.client.CasOAuthWrapperClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author huoshan
 * created by 2017年11月1日 上午8:37:35
 * 
 */
@Component
public class AbstractController {
	
	static CasOAuthWrapperClient client;
	
	@Value("${oauth2.client_id}")
	private String clientId;
	@Value("${oauth2.client_secret}")
	private String clientSecret;
	@Value("${oauth2.cas_server_url_prefix}")
	private String casServerUrlPrefix;
	@Value("${oauth2.call_back_url}")
	private String callbackUrl;
	
	@PostConstruct
	public void init(){
		client = new CasOAuthWrapperClient(clientId, clientSecret, casServerUrlPrefix);
	    client.setCallbackUrl(callbackUrl);
	}
	
}
