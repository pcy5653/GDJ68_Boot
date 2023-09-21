package com.winter.app.config;


import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration  // springBoot 실행 시, 가장 먼저 읽히는 파일
public class MessageConfig implements WebMvcConfigurer {

	
	// Message 설정 시, session || Cookie 사용 (2중 1택)
	
	@Bean // Library를 사용하기 때문에 개발자가 직접 객체생성
	public LocaleResolver localeResolver() {
		
		// 1ver. session 이용하여 Message Default 설정
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.KOREAN);
		
			
		// 2ver. Cookie 이용하여 Message Default 설정
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(Locale.KOREAN);
		cookieLocaleResolver.setCookieName("lang");
		
		return resolver; // cookieLocaleResolver;
	}
	
	
	// Message Interceptor 객체 생성
	// ==> Message 시에 interceptor 처리
	@Bean
	public LocaleChangeInterceptor changeInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		
		// 파라미터로 언어 받기
		interceptor.setParamName("lang");
		
		// 파라미터로 받아서 언어 구분
		
		
		// url?lang=en
		// url?lang=ko
		
		return interceptor;
	}
	
}
