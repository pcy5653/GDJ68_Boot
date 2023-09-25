package com.winter.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.winter.app.interceptors.TestInterceptor;

//**-context.xml 파일과 같은 역할
@Configuration  // springBoot가 실행될 때 가장 먼저 읽기 (설정파일)
public class InterceptorConfig implements WebMvcConfigurer  {

	@Autowired
	private TestInterceptor testInterceptor;
	@Autowired
	private LocaleChangeInterceptor localeChangeInterceptor;
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// 적용할 interceptor 등록
		registry.addInterceptor(testInterceptor)
				// interceptor을 적용할 URL 등록
				.addPathPatterns("/notice/list");  // 해당 경로를 올때마다 testInterceptor 실행
	
	
	
		// Message interceptor
		registry.addInterceptor(localeChangeInterceptor)
				.addPathPatterns("/**");
	}
	
	
}
