package com.example.cas.client.demo.controller;

import java.io.IOException;
import java.io.Writer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.exception.HttpAction;
import org.pac4j.oauth.credentials.OAuth20Credentials;
import org.pac4j.oauth.profile.casoauthwrapper.CasOAuthWrapperProfile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author huoshan
 * created by 2017年11月1日 上午8:44:03
 * 
 */
@Controller
public class AuthorizationController extends AbstractController{
	
	@RequestMapping("/")
	public void index(HttpServletRequest request , HttpServletResponse response) throws IOException{
		 Writer write = response.getWriter();
		 write.write("<html><body><center>");
		 write.write("<h3>");
		 write.write("<a href='/authorz'>cas oauth2</a>");
		 write.write("</h3>");
		 write.write("</center></body></html>");
		 write.flush();
		 write.close();
	}
	
	@RequestMapping(value = "/authorz")
	public void authorz(HttpServletRequest request , HttpServletResponse response){
		try {
            WebContext ctx = new J2EContext(request, response);
            HttpAction redirect = client.redirect(ctx);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
	@RequestMapping(value="/callback")
	public void callback(HttpServletRequest request, HttpServletResponse response) throws IOException, HttpAction{
		 WebContext ctx = new J2EContext(request, response);
         OAuth20Credentials credentials = client.getCredentials(ctx);
         CasOAuthWrapperProfile profile = client.getUserProfile(credentials, ctx);
         Writer w = response.getWriter();
         String code = credentials.getCode();
         String accessToken = profile.getAccessToken();
         w.write("<html><body><center>");
         w.write("<h3>");
         w.write("User [" + profile.getId() + "] has successfully logged in!");
         w.write("<h3>code:[ " + code + "]</h3>");
         w.write("Access Token[" + accessToken + "]</h3>");
         w.write("</h3>");
         w.write("</center></body></html>");
         w.flush();
         w.close();
	}
	
}
