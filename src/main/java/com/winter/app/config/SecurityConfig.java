package com.winter.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//**-context.xml 파일과 같은 역할
@Configuration  // springBoot 실행 시, 가장 먼저 읽히는 파일 (설정파일)
@EnableWebSecurity  // Security 활성화
public class SecurityConfig {
	
	@Autowired
	private SecuritySuccessHandler handler;
	
	// login 시 pw 암호화 (DB와 login시 pw 비번과 일치확인)
	// pw를 암호화해서 db에 집어넣고 login때 암호화된 비번과 일치한지 확인 후 index로 넘어감
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Bean  //API 이기 때문에 개발자가 스스로 객체 생성
	WebSecurityCustomizer webSecurityCustomizer() {
		// Security에서 무시해야할 URL 패턴 등록 (front 소스)
		
		return web -> web
				.ignoring()
				.antMatchers("/css/**")
				.antMatchers("/img/**")
				.antMatchers("/js/**")
				.antMatchers("/vendor/**")
				;
	}
	
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception {
		// 모든 권한 허용
		httpSecurity
			.cors()
			.and()
			.csrf()
			.disable()
			.authorizeHttpRequests()
				.antMatchers("/notice/add").hasRole("ADMIN")     // login한 관리자만 허용 (table => ROLE_ADMIN에서 " ROLE_ " 제외)
				// .antMatchers("/manager/*").access("hasRole('ROLE_ADMIN')") ????????
				.antMatchers("/manager/*").hasAnyRole("ADMIN", "MANAGER")  // 여러개 중에 하나의 권한만 있어도 허용
				// .antMatchers("/notice/add").authenticated()   // login 한 사용자만 허용 (403에러 : 요청된 리소스에 접근할 권한이 없거나 금지)
				.antMatchers("/").permitAll()				     // 루트 뒤 경로 모든 권한 누구나 허용
				.and()
			
			// form 관련 설정
			.formLogin()
				.loginPage("/member/login")		// login을 처리하는(post) 주소. (security에서 가로채서 Controller가지 않고 바로 메서드 실행, loadUserByUsername 메서드 실행)  내장된 login form을 사용하지 않고 개발자가 만든 form을 사용. (Controller POST(login) 주석)
				// .defaultSuccessUrl("/")      // login 성공시 [1.ver]
				.successHandler(handler)        // SecuritySuccessHandler의 객체를 가져옴. login 성공시 [2.ver 사용!! -> 후처리 할 시 사용.]
				//.failureUrl("/member/login?message=LoginFail")    // login 실패시 [1.ver] (실패했을 때, 파라미터 나타남)
				.failureHandler(getFailHandler()) // 메서드를 만들어서 불러오기 (객체생성대신) [2.ver]
				.permitAll()                    // login 경로로 가는건 모두 허용	
				.and()
			.logout()
				.logoutUrl("/member/logout")	// logout을 처리하는(post) 주소.
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)    // logout 했을때 시간을 0으로 변경. (Legacy때 invalidate를 0으로 준 것과 동일.)
				.and()
			.sessionManagement()
			;
		return httpSecurity.build();
	}
	
	
	SecurityFailHandler getFailHandler() {
		return new SecurityFailHandler();
	}
}
