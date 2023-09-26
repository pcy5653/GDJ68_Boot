package com.winter.app.config;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

//<< 로그인 실패 >>
@Component
@Slf4j
public class SecurityFailHandler implements AuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		log.info("=========== Exception : {} ================ ", exception);
		
		String message = "로그인 실패";
		
		if(exception instanceof InternalAuthenticationServiceException) {
			// 아이디 여부 확인 : exception이 아이디가 없는 경우
			// message="아이디가 존재하지 않습니다.";
			message = "login.fail.nouser";
		}
		
		if(exception instanceof BadCredentialsException) {
			// 비번 확인 : exception이 틀린 비번일 경우
			// message="비밀번호가 틀렸습니다.";
			message = "login.fail.notpassword";
		}
		
		if(exception instanceof AccountExpiredException) {
			// 계정 유효기간 만료 
			message="계정이 만료되었습니다. 관리자에게 문의하세요.";
		}
		
		if(exception instanceof LockedException) {
			// 잠긴 계정 
			message="잠긴 계정입니다. 관리자에게 문의하세요.";
		}
		
		if(exception instanceof CredentialsExpiredException) {
			// 비밀번호 유효기간 만료 
			message="만료된 비밀번호 입니다. 관리자에게 문의하세요.";
		}
		
		if(exception instanceof DisabledException) {
			// 휴먼 계정 
			message="휴먼 계정 입니다. 관리자에게 문의하세요.";
		}
		
		
		message = URLEncoder.encode(message, "UTF-8");
		response.sendRedirect("/member/login?message="+message);
	}

	
}
