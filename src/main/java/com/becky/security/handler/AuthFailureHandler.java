package com.becky.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.becky.security.util.RequestUtil;

@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		logger.info("fail login: " + exception.getMessage());

		if(RequestUtil.isAjax(request)){
			JSONObject json = new JSONObject();
			json.put("success", false);
			RequestUtil.responseWrite(response, json);
		}else{
			super.onAuthenticationFailure(request, response, exception);
		}
		
	}
}
