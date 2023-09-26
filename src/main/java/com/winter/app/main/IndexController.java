package com.winter.app.main;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.winter.app.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {
	
	@GetMapping("/")
	public String getIndex(HttpSession session)throws Exception{
		// session의 속성명인 "SPRING_SECURITY_CONTEXT" 을 통해 로그인한 사용자의 전체 정보를 알 수 있음.
//		Enumeration<String> en = session.getAttributeNames();
//		
//		while(en.hasMoreElements()) {
//			// hasMoreElements : 요소가 있으면 true, 없으면 false
//			String name = en.nextElement();
//			log.info("================= Attribute : {} ", name);
//		}
		
		//Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		
		SecurityContext con = SecurityContextHolder.getContext();
		Authentication b = con.getAuthentication();
		
		// 접근주체의 모든 정보에서 원하는 정보를 선택하기 위해서는 MemberVO로 형변환을 해본다. (bootstrap 문제인가 되지 않음.)
		//MemberVO member = (MemberVO)b.getPrincipal();
		
		log.info("=================>>>>>>>>> GetName : {} ", b.getName());			// username (sunday1)
			
		log.info("=================>>>>>>>>> GetPrincipal : {} ", b.getPrincipal()); // principal : 접근주체(현재 사용자)의 정보
		
		//log.info("=================>>>>>>>>> GetPrincipal : {} ", member.getBirth()); 
		
		log.info("=================>>>>>>>>> GetAuthority : {} ", b.getAuthorities() ); // authorities : 현재 사용자가 갖고 있는 권한들 (ROLE_MEMBER)
		return "index";
	}
}
