package com.winter.app.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityLogoutAdd implements LogoutHandler {
	
	
	// << addLogoutHandler(obj) -- 로그아웃 할 시, 해야 할 기능을 구성 >>
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		
		log.info("=====LOGOUT ADD=============");
	}
	
}
