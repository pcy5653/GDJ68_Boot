package com.winter.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// **-context.xml 파일과 같은 역할

@Configuration  // springBoot가 실행될 때 가장 먼저 읽기
public class FileMappingConfig implements WebMvcConfigurer {

	// local file 위치
	@Value("${app.upload.mapping}")
	private String filePath;
	
	// 요청 URL 경로 : 해당 경로로 들어오는 것은 local file 경로로 보내져라.
	@Value("${app.url.path}")
	private String urlPath;
	
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		
		// 요청 URL : /files/** 경로로 들어온 요청은
		registry.addResourceHandler(urlPath)
		
		// local file 위치 : file:///D:///pcy/upload/ 이 경로로 생각하여 뒤에 더 붙여서 파일 요청 진행.
				.addResourceLocations(filePath);
	}
}
