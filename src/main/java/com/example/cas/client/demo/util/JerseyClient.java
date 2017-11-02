package com.example.cas.client.demo.util;

import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author huoshan
 * created by 2017年6月4日 下午1:37:54
 * 
 */
public class JerseyClient {
	
	public static String rest(RequestMethod method ,String requestUrl , List<String> pathList, Map<String,Object> headParams, Map<String,Object> queryParams, String requestData){
		
		Client client = HttpConnectPool.newClient();
		WebTarget webTarget = client.target(requestUrl);
		if(pathList != null){
			for (String path : pathList) {
				webTarget = webTarget.path(path);
			}
			
		}
		//构造查询参数
		if(queryParams != null){
			for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
				  webTarget = webTarget.queryParam(entry.getKey(), entry.getValue());
			}
		}
		
		Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		//构造请求头
		if(headParams != null){
			for(Map.Entry<String, Object> entry : headParams.entrySet()){
				invocationBuilder.header(entry.getKey(), entry.getValue());
			}
		}
		//发起请求
		Response response;
		Entity<String> entity;
		switch (method) {
			case GET:
				response = invocationBuilder.get();
				return response.readEntity(String.class);
			case POST:
				entity = Entity.entity(requestData, MediaType.APPLICATION_JSON);
				response = invocationBuilder.post(entity);
				MultivaluedMap<String, Object> headers = response.getHeaders();
				return response.readEntity(String.class);
			case PUT:
				entity = Entity.entity(requestData, MediaType.APPLICATION_JSON);
				response = invocationBuilder.put(entity);
				return response.readEntity(String.class);
			case DELETE:
				response = invocationBuilder.delete();
				return response.readEntity(String.class);
			default:
				return null;
		}
	}
}
