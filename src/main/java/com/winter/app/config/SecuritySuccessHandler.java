package com.winter.app.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


// << 로그인 성공 >>
@Component
@Slf4j
public class SecuritySuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// authentication : 로그인 성공 시, 사용자의 정보를 가져온다.!!! SecurityConfig에 사용함.
		// 권한있는 페이지(add, update, delete에서 비로그인으로 접속하면 로그인페이지 -> 로그인 -> add, update, delete 페이지로 이동)로 로그인시 이동을 막기 위함.
		// 어디서 로그인해도 "/"로 보내지기 위함.
		log.info("===================>>>>> Authentication : {} ===========", authentication);
		
		log.info("===============>>>>> path Info : {} ", request.getPathInfo());
		
		log.info("===============>>>>> getRequestURI : {} ", request.getRequestURI());
		
		log.info("===============>>>>> getRequestURL : {} ", request.getRequestURL());
		
		// 로그인 성공시, "/" 이동.
		response.sendRedirect("/");
		
	}

	
}
