package com.example.cas.client.demo;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.cas.client.demo.util.JerseyClient;


/**
 * @author huoshan
 * created by 2017年11月1日 上午9:45:46
 * 
 */
public class Oauth2CasPasswordTestDemo {
	
	/* ------------ need config ------------*/
	
	String baseUrl = "https://cas.example.org:8443/cas/oauth2.0/";
	String clientId = "clientid";
	String clientSecret = "clientSecret";
	String username = "1416236046@qq.com";
	String password = "123456";
	
	/*-------------------------------------------*/
	
	String accessToken;
	String refresToken;
	String expires;
	String accessTokenParamKey = "access_token";
	String refreshTokenParamKey = "refresh_token";
	String expiresInParamKey = "expires_in";
	
	String accessTokenByPassUrl = baseUrl + "accessToken?grant_type=password&client_id="+ clientId +
			"&username="+ username +"&password=" + password;
	String profileUrl = baseUrl + "/profile?access_token=";
	String refreshTokenUrl = baseUrl + "/accessToken?grant_type=refresh_token&client_id="+clientId+
			"&client_secret="+ clientSecret +"&refresh_token=";
	
	
	@Test
	public void testGetAccessToken(){
		
		String response = JerseyClient.rest(RequestMethod.POST, accessTokenByPassUrl, null, null, null, null);
		Map<String, Object> accessTokenMap = getUrlParams(response);
		this.accessToken = (String) accessTokenMap.get(this.accessTokenParamKey);
		this.refresToken = (String) accessTokenMap.get(this.refreshTokenParamKey);
		this.expires = (String) accessTokenMap.get(this.expiresInParamKey);
		System.out.println("get accessToken response:->" + accessTokenMap);
	}
	
	@Test
	public void testGetProfile(){
		this.testGetAccessToken();
		this.profileUrl += this.accessToken;
		String response = JerseyClient.rest(RequestMethod.GET, profileUrl, null, null, null, null);
		System.out.println("get profile response:->" + response);
	}
	
	@Test
	public void testRefreshToken(){
		this.testGetAccessToken();
		this.refreshTokenUrl += refresToken;
		String response = JerseyClient.rest(RequestMethod.POST, refreshTokenUrl, null, null, null, null);
		System.out.println("refreshToken response:-->" + response);
	}
	
	public Map<String, Object> getUrlParams(String param) {  
        Map<String, Object> map = new HashMap<String, Object>(0);  
        if (StringUtils.isEmpty(param)) {  
            return map;  
        }  
        String[] params = param.split("&");  
        for (int i = 0; i < params.length; i++) {  
            String[] p = params[i].split("=");  
            if (p.length == 2) {  
                map.put(p[0], p[1]);  
            }  
        }  
        return map;  
    }  
}
