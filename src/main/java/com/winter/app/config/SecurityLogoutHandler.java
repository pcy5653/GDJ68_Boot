package com.winter.app.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityLogoutHandler implements LogoutSuccessHandler {
	
	// << logoutSuccesHandler(obj) -- 로그아웃 성공 후, 개발자가 추가 할  기능을 구성 >>
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		log.info("======= Logout Authentication : {} ===========", authentication);
		
		// 응답으로 redirect:/ 를 보냄.
		response.sendRedirect("/");
	}

	
	
}
