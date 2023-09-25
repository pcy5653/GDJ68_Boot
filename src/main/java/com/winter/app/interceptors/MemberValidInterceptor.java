package com.winter.app.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


/**
 * Valid(검증) 
 * Member Update 시 
 * Password Valid 제외
 * */

@Component
public class MemberValidInterceptor implements HandlerInterceptor {
	
	// Controller 진입 전 실행 = preHandle
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		/**
		 * <<interceptor로 검증하려고 했는데 안되는 이유>>
		 * => parameter를 생성해서 추가하는 메서드가 존재하지 않음.
		 * 
		 * */
		
		return true;
	}
	
}
