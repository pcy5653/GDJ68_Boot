package com.winter.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//**-context.xml 파일과 같은 역할
@Configuration   // springBoot 실행 시, 가장 먼저 읽히는 파일 (설정파일)
public class SecurityPasswordEncoder {

	// << Security Config에서 사용 >>	
	// login 시 pw 암호화 (DB와 login시 pw 비번과 일치확인)
	// pw를 암호화해서 db에 집어넣고 login때 암호화된 비번과 일치한지 확인 후 index로 넘어감
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
